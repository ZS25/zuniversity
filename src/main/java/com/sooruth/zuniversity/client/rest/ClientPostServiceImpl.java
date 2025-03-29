package com.sooruth.zuniversity.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
public final class ClientPostServiceImpl implements ClientPostService {

    private final Logger LOG = LoggerFactory.getLogger(ClientPostServiceImpl.class);

    private final RestClient restClient;

    public ClientPostServiceImpl() {
        this.restClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/").build();
    }

    @Override
    public Long create(PostRecord postRecord) {
        PostRecord createdPostRecord = restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(postRecord)
                .retrieve()
                .body(PostRecord.class);

        Objects.requireNonNull(createdPostRecord, "PostRecord has not been created!");

        return ((long) createdPostRecord.id().intValue());
    }

    @Override
    public PostRecord read(Long id) {
        PostRecord postRecord;
        try{
            postRecord = restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(PostRecord.class);
        }
        catch (HttpClientErrorException httpClientErrorException){
            LOG.error(httpClientErrorException.toString());
            throw new IllegalArgumentException(String.format("Post with ID: %d not found!", id));
        }
        return postRecord;
    }

    @Override
    public Page<PostRecord> readAll(int page, int size) {
        List<PostRecord> postRecordList =  restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (CollectionUtils.isEmpty(postRecordList)){
            throw new RuntimeException("No post record found!");
        }

        return pagePostRecordList(page, size, postRecordList);
    }

    private static PageImpl<PostRecord> pagePostRecordList(int page, int size, List<PostRecord> postRecordList) {
        Pageable pageRequest = PageRequest.of(page, size);

        int startIndice = (int) pageRequest.getOffset();
        int endIndice = Math.min((startIndice + pageRequest.getPageSize()), postRecordList.size());

        List<PostRecord> pageContent = postRecordList.subList(startIndice, endIndice);

        return new PageImpl<>(pageContent, pageRequest, postRecordList.size());
    }

    @Override
    public void update(PostRecord postRecord) {
        restClient.put()
                .uri("/posts/{id}", postRecord.id())
                .contentType(MediaType.APPLICATION_JSON)
                .body(postRecord)
                .retrieve()
                .body(PostRecord.class);
    }

    @Override
    public void delete(Long id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}

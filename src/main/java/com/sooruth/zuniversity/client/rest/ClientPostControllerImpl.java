package com.sooruth.zuniversity.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/client/posts")
class ClientPostControllerImpl implements ClientPostController {

    private final Logger LOG = LoggerFactory.getLogger(ClientPostControllerImpl.class);

    private final ClientPostService clientPostService;

    public ClientPostControllerImpl(ClientPostService clientPostService) {
        this.clientPostService = clientPostService;
    }

    @Override
    public Page<PostRecord> getAll(int page, int size) {
        return clientPostService.readAll(page, size);
    }

    @Override
    public PostRecord getById(Long id) {
        return clientPostService.read(id);
    }

    @Override
    public ResponseEntity<String> save(PostRecord model) {
        Long savedPostId = clientPostService.create(model);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedPostId).toUri()).build();
    }

    @Override
    public PostRecord modify(PostRecord model) {
        return clientPostService.update(model);
    }

    @Override
    public void delete(Long id) {
        clientPostService.delete(id);
    }
}

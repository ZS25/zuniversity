package com.sooruth.zuniversity.client.rest;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/client/posts")
public final class ClientPostControllerImpl implements ClientPostController {
    private final ClientPostService clientPostService;

    public ClientPostControllerImpl(ClientPostService clientPostService) {
        this.clientPostService = clientPostService;
    }

    @Override
    public Page<PostRecord> readAll(int page, int size) {
        return clientPostService.readAll(page, size);
    }

    @Override
    public PostRecord readById(Long id) {
        return clientPostService.read(id);
    }

    @Override
    public HttpEntity<EntityModel> create(PostRecord model) {
        Long savedPostId = clientPostService.create(model);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPostId)
                .toUri();

        Link selfLink = linkTo(methodOn(ClientPostControllerImpl.class).readById(savedPostId)).withSelfRel();
        EntityModel<PostRecord> resource = EntityModel.of(model, selfLink);
        return ResponseEntity.created(location).body(resource);

    }

    @Override
    public void update(PostRecord model) {
        clientPostService.update(model);
    }

    @Override
    public void delete(Long id) {
        clientPostService.delete(id);
    }
}

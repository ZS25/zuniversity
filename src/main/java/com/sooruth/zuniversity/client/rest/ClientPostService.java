package com.sooruth.zuniversity.client.rest;

import com.sooruth.zuniversity.service.EntityService;

public sealed interface ClientPostService extends EntityService<PostRecord> permits ClientPostServiceImpl {
}

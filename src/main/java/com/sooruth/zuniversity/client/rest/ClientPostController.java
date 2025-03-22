package com.sooruth.zuniversity.client.rest;

import com.sooruth.zuniversity.controller.ModelController;

public sealed interface ClientPostController extends ModelController<PostRecord> permits ClientPostControllerImpl{
}

package com.miu.service;

import com.miu.enums.MediaType;

public interface MediaClient {
    boolean isValidMedia(int id, MediaType mediaType);
    Object getMediaById(int id, MediaType mediaType);
}

package com.edu.miu.client;

import com.edu.miu.enums.MediaType;

public interface MediaClient {

    boolean isValidMedia(int id, MediaType mediaType);

    Object getMediaById(int id, MediaType mediaType);

}

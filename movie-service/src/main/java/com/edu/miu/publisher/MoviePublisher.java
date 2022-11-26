package com.edu.miu.publisher;

import com.edu.miu.dto.MediaDto;

public interface MoviePublisher {

    void sendRemovedMovieMessage(MediaDto mediaDto);

}

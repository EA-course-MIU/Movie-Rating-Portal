package com.example.service;

import com.example.dto.MediaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("MEDIA-SERVICE")
public interface MediaClient {
    @GetMapping("/medias")
    Iterable<MediaDto> getAllMedia();
}

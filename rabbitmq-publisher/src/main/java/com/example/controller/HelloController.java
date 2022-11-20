package com.example.controller;

import com.example.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping
    public void send() {
        helloService.send();
        helloService.directSend();
    }

}

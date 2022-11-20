package com.example.service;

public interface HelloConsumer {
    void bindToHelloQueue(String payload);
    void bindToDirectQueue(String payload);
    void receive2(String in) throws InterruptedException;
}

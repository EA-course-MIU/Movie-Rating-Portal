package com.example.service.impl;

import com.example.service.HelloConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class HelloConsumerImpl implements HelloConsumer {

    @Override
    @RabbitListener(queues = {"hello-queue-1"})
    public void bindToHelloQueue(String payload) {
        System.out.println(payload);
    }
    @RabbitListener(queues = {"spring.gen-0Bw2MVl3RDu0qMpeCMyZWQ"})
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = {"spring.gen-1M4XH3DFRtuBdEzeu8YNdw"})
    @Override
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + receiver + " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + receiver + " [x] Done in " +
                watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
    @Override
    @RabbitListener(queues = {"spring.gen-1M4XH3DFRtuBdEzeu8YNdw"})
    public void bindToDirectQueue(String payload) {
        System.out.println(payload);
    }
}

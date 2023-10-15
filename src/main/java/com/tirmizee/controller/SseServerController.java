package com.tirmizee.controller;

import com.tirmizee.service.SseServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SseServerController {

    @Autowired
    private SseServerService sseServerService;

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> seeEvents() {
        return sseServerService.processorInstance();
    }

    @GetMapping(path = "/trigger")
    public Mono<Void> triggerEvents(@RequestParam String message) {
        sseServerService.triggerEvent(message);
        return Mono.empty();
    }


}

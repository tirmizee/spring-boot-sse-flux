package com.tirmizee.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class SseClientService {

    private final WebClient webClient;

    public SseClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Flux<String> streamEvents() {
        return webClient.get()
                .uri("/sse")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);
    }

    public void handleMessage(String message) {
        System.out.println("Received: " + message);
    }

    public void handleError(Throwable error) {
        System.err.println("Error: " + error);
    }

}

package com.tirmizee.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;

@Service
public class SseServerService {

    private final EmitterProcessor<String> processor;

    public EmitterProcessor<String> processorInstance() {
        return processor;
    }

    public SseServerService() {
        this.processor = EmitterProcessor.create();
    }

    public void triggerEvent(String eventData) {
        processor.onNext(eventData);
    }

}

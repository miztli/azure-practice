package com.example.springsleuth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

@Service
public class IntensiveProcessingService {
    private static final Logger LOG = LoggerFactory.getLogger(IntensiveProcessingService.class);

    @Value("${intensive-service.duration:5000}")
    private long duration;

    @NewSpan("cpuIntensiveMethodSimulation")
    public void cpuIntensiveMethodSimulation() throws InterruptedException {
        LOG.info("Executing CPU intensive method of duration: {}", duration);
        Thread.sleep(duration);
    }
}

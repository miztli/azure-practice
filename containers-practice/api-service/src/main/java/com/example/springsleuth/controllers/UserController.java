package com.example.springsleuth.controllers;

import com.example.springsleuth.model.Employee;
import com.example.springsleuth.model.EmployeeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class UserController {

    /**
     * Spring Cloud Sleuth with Brave tracer will provide instrumentation of the incoming request.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private IntensiveProcessingService intensiveProcessingService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(final @RequestBody Employee employee) {
        LOG.info("Creating new employee");
        final var requestEntity = new HttpEntity<>(employee);
        restTemplate.exchange("/employee", HttpMethod.POST, requestEntity, Void.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeList> findEmployees() {
        return restTemplate.getForEntity("/employee", EmployeeList.class);
    }

    @GetMapping(value = "/intensive")
    @ResponseStatus(HttpStatus.OK)
    public void intensiveProcess() throws InterruptedException {
        LOG.info("Calling intensive CPU process");
        intensiveProcessingService.cpuIntensiveMethodSimulation();
    }
}

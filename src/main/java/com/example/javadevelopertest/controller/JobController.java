package com.example.javadevelopertest.controller;

import com.example.javadevelopertest.model.LoginResponse;
import com.example.javadevelopertest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/job")
    public ResponseEntity<?> getAllJob() {
        try {
            return ResponseEntity.ok(jobService.getJob());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/job/{id}")
    public  ResponseEntity<?> getJobById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(jobService.getJobById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

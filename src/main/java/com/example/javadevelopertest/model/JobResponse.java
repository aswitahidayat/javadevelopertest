package com.example.javadevelopertest.model;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private List<JobDetail> jobDetailList;
}

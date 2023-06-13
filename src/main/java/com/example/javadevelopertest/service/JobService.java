package com.example.javadevelopertest.service;

import com.example.javadevelopertest.model.JobDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class JobService {
    public JobDetail[] getJob() throws IOException {
        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json"; // Menambahkan ID ke URL
        HttpURLConnection connection = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return mapper.readValue(connection.getInputStream(), JobDetail[].class);
            } else {
                throw new IOException("HTTP request failed with response code: " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public JobDetail getJobById(String id) throws IOException {
        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id; // Menambahkan ID ke URL
        HttpURLConnection connection = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return mapper.readValue(connection.getInputStream(), JobDetail.class);
            } else {
                throw new IOException("HTTP request failed with response code: " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

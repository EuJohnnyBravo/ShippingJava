package com.rodrigo.fretecalculator.implementation.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.fretecalculator.common.exception.custom.ExternalServiceException;
import com.rodrigo.fretecalculator.contract.viaCep.response.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViaCepService {

    public ViaCepResponse getViaCep(String destination) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + destination + "/json/"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ExternalServiceException("Failed to consult destination: " + response.body());
            }
            return new ObjectMapper().readValue(response.body(), ViaCepResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new ExternalServiceException("Failed to access ViaCep Service: " + e.getMessage());
        }
    }
}

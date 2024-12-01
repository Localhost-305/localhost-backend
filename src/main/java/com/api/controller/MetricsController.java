package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    // Endpoint que recebe o valor TTI do front-end
    @PostMapping("/metrics/tti")
    public ResponseEntity<Map<String, String>> saveTti(@RequestBody TtiRequest ttiRequest) {
        // Aqui você processa o valor do TTI
        System.out.println("TTI recebido: " + ttiRequest.getTti());

        // Criar uma resposta em formato JSON
        Map<String, String> response = new HashMap<>();
        response.put("message", "TTI recebido com sucesso!");
        response.put("tti", String.valueOf(ttiRequest.getTti()));

        // Retorna uma resposta em JSON
        return ResponseEntity.ok(response);
    }
}

package com.api.controller;

import com.api.domain.service.ExportExcelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportExcelController {

    @Autowired
    ExportExcelService exportExcelService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportData(@RequestParam String dataString) {
        try {
            System.out.println(dataString);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> data = objectMapper.readValue(dataString, List.class);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            exportExcelService.exportToExcel(data, outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
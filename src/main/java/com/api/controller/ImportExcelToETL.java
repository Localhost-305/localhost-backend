package com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ImportExcelToETL {

  private static final String DIRECTORY = Paths.get(System.getProperty("user.dir"), "..\\localhost-etl\\", "localhost-etl").toString();

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Por favor, selecione um arquivo!");
        }

        try {
            File dest = new File(DIRECTORY + file.getOriginalFilename());
            file.transferTo(dest); 

            return ResponseEntity.ok("Arquivo salvo com sucesso em " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}

package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.entity.User;
import com.api.domain.service.EmailService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/excel")
public class ImportExcelToETL {

    private static final String DIRECTORY = Paths.get(System.getProperty("user.dir"), "..", "localhost-etl").toString();
    private static final String DIRECTORY_ERRORS = Paths.get(System.getProperty("user.dir"), "..", "localhost-etl", "etl_project", "src", "error_files", "LOG_ERRORS_JSON").toString();
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Por favor, selecione um arquivo!");
            }

            try {
                File dest = new File(DIRECTORY + File.separator + file.getOriginalFilename());
                file.transferTo(dest);

                String errorLogContent = "";
                File errorFile = new File(DIRECTORY_ERRORS + File.separator + file.getOriginalFilename().replaceFirst("\\.[^.]+$", ".json"));
                if (errorFile.exists()) {
                    errorLogContent = new String(Files.readAllBytes(errorFile.toPath()), StandardCharsets.UTF_8);
                }

                String subject = "Importação de arquivo";
                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("Olá ").append(user.getName()).append(",\n\nSeu arquivo foi importado com sucesso, os dados foram processados e adicionados.");

                if (!errorLogContent.isEmpty()) {
                    messageBuilder.append("\n\nEntretanto, ocorreram alguns erros durante o processamento:\n").append(errorLogContent);
                }

                String message = messageBuilder.toString();

                emailService.sendEmail(user.getEmail(), subject, message);

                return ResponseEntity.ok("Arquivo salvo com sucesso em " + dest.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(403).body("Usuário não autenticado!");
        }
    }
}

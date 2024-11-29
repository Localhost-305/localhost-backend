package com.api.domain.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExportPdfService {
    public void exportToPdf(List<Map<String, Object>> dados, ByteArrayOutputStream outputStream) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Configurações de fonte e posição inicial
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);

                // Gera os cabeçalhos da tabela
                if (!dados.isEmpty()) {
                    List<String> headers = List.copyOf(dados.get(0).keySet());
                    String headerLine = String.join(" | ", headers);
                    contentStream.showText(headerLine);
                    contentStream.newLineAtOffset(0, -20); // Próxima linha
                    contentStream.showText("=".repeat(headerLine.length()));
                    contentStream.newLineAtOffset(0, -20);

                    // Gera as linhas da tabela
                    for (Map<String, Object> row : dados) {
                        String rowLine = headers.stream()
                                .map(header -> row.getOrDefault(header, "").toString())
                                .reduce((a, b) -> a + " | " + b)
                                .orElse("");
                        contentStream.showText(rowLine);
                        contentStream.newLineAtOffset(0, -20);
                    }
                } else {
                    contentStream.showText("Nenhum dado disponível para exportar.");
                }

                contentStream.endText();
            }

            document.save(outputStream);
        }
    }
}

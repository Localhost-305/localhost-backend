package com.api.domain.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ExportExcelService {
    public void exportToExcel(List<Map<String, Object>> dados, ByteArrayOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados");

        int rowNum = 0;
        Set<String> allHeaders = new LinkedHashSet<>();
        allHeaders.addAll(dados.get(0).keySet());

        Row headerRow = sheet.createRow(rowNum++);
        List<String> headers = new ArrayList<>(allHeaders);
        for (int colNum = 0; colNum < headers.size(); colNum++) {
            headerRow.createCell(colNum).setCellValue(headers.get(colNum));
        }

        for (Map<String, Object> linha : dados) {
            Row row = sheet.createRow(rowNum++);
            for (int colNum = 0; colNum < headers.size(); colNum++) {
                String header = headers.get(colNum);
                Object cellValue = linha.get(header);
                if (cellValue != null) {
                    row.createCell(colNum).setCellValue(cellValue.toString());
                } else {
                    row.createCell(colNum).setCellValue("");
                }
            }
        }

        workbook.write(outputStream);
        workbook.close();
    }
}

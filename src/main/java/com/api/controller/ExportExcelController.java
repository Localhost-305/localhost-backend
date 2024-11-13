//package com.api.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.util.Date;
//import java.util.List;
//import java.io.IOException;
//import java.io.ByteArrayOutputStream;
//
//import com.api.domain.service.JobService;
//
////@RestController
////@RequestMapping("/job")
//public class ExportExcelController {
//
////    @Autowired
////    private JobService jobService;
//
//    public static void exportar(List<Object[]> dados, ByteArrayOutputStream outputStream) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Dados");
//
//        CreationHelper creationHelper = workbook.getCreationHelper();
//        CellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
//
//        int rowNum = 0;
//        for (Object[] linha : dados) {
//            Row row = sheet.createRow(rowNum++);
//            for (int colNum = 0; colNum < linha.length; colNum++) {
//                Cell cell = row.createCell(colNum);
//
//                if (linha[colNum] instanceof String) {
//                    cell.setCellValue((String) linha[colNum]);
//                } else if (linha[colNum] instanceof Integer) {
//                    cell.setCellValue((Integer) linha[colNum]);
//                } else if (linha[colNum] instanceof Double) {
//                    cell.setCellValue((Double) linha[colNum]);
//                } else if (linha[colNum] instanceof Boolean) {
//                    cell.setCellValue((Boolean) linha[colNum]);
//                } else if (linha[colNum] instanceof Date) {
//                    cell.setCellValue((Date) linha[colNum]);
//                    cell.setCellStyle(dateCellStyle);
//                } else {
//                    cell.setCellValue(linha[colNum].toString());
//                }
//            }
//        }
//
//        workbook.write(outputStream);
//        workbook.close();
//    }
//
////    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportData(List<Object[]> dados) {
//        try {
////            List<Object[]> dados = List.of(
////                    new Object[] {"Nome", "Idade", "Salário", "Data de Nascimento"},
////                    new Object[] {"João", 30, 2500.0, new Date(93, 0, 1)},  // Data de 1 de janeiro de 1993
////                    new Object[] {"Maria", 28, 3000.0, new Date(94, 3, 15)}, // Data de 15 de abril de 1994
////                    new Object[] {"José", 35, 3500.0, new Date(88, 7, 20)}   // Data de 20 de agosto de 1988
////            );
//
//
////            System.out.println("\n\n");
////            System.out.println(dados.get(0));
////            System.out.println("\n\n");
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            exportar(dados, outputStream);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment; filename=dados_com_data.xlsx");
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
//                    .body(outputStream.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}

package com.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;



@RestController
@RequestMapping("/export")
public class ExportExcelController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public ResponseEntity<byte[]> exportData2(String dataString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> data = objectMapper.readValue(dataString, List.class);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            exportar(data, outputStream);

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

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportData(String endpoint, String jwtToken) {
        try {

            System.out.println("\n\nendpoint: "+endpoint);

            endpoint = endpoint.replace(":","/");
//            String token = getJwtTokenFromSecurityContext();
            System.out.println("endpoint trocado: "+endpoint);
            System.out.println("\n\n");

            List<Map<String, Object>> dados = getExternalData(endpoint, jwtToken);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exportar(dados, outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=dados_com_data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<Map<String, Object>> getExternalData(String endpoint, String jwtToken) {
        String url = "http://localhost:9090/"+endpoint;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

        return response.getBody();
    }

    private static void exportar(List<Map<String, Object>> dados, ByteArrayOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados");

        int rowNum = 0;
        Set<String> allHeaders = new LinkedHashSet<>();

        for (Map<String, Object> linha : dados) {
            allHeaders.addAll(linha.keySet());
        }

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

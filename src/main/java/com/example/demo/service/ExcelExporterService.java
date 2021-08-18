package com.example.demo.service;

import com.example.demo.models.Bank;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporterService {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;


    public ExcelExporterService() {

        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Banks");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "short Name", style);
        createCell(row, 0, "Arabic Name", style);
        createCell(row, 0, "Main_Branch_ID", style);
        createCell(row, 0, "name", style);
        createCell(row, 0, "fiid", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(List<Bank> bankList) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Bank user : bankList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getShort_Name(), style);
            createCell(row, columnCount++, user.getArabicname(), style);
            createCell(row, columnCount++, user.getMain_Branch_ID(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getFIID(), style);


        }
    }
    private void writeDataLines(Bank bankList) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        Row row = sheet.createRow(rowCount++);
        int columnCount = 0;
        createCell(row, columnCount++, bankList.getShort_Name(), style);
        createCell(row, columnCount++, bankList.getArabicname(), style);
        createCell(row, columnCount++, bankList.getMain_Branch_ID(), style);
        createCell(row, columnCount++, bankList.getName(), style);
        createCell(row, columnCount++, bankList.getFIID(), style);



    }

    public void export(HttpServletResponse response, List<Bank> bankList) throws IOException {
        writeHeaderLine();
        writeDataLines(bankList);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
    public void export(HttpServletResponse response, Bank bankList) throws IOException {
        writeHeaderLine();
        writeDataLines(bankList);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

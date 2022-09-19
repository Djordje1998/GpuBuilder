/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;

/**
 *
 * @author LightPower
 */
public class ExcelFileExporter {

    public static ByteArrayInputStream exportToExcel(ComputerDto dto) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Computer");

        Row row = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(1);
        cell.setCellValue("Name");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(2);
        cell.setCellValue("Usage");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(3);
        cell.setCellValue("Item Count");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(4);
        cell.setCellValue("Warranty");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(5);
        cell.setCellValue("Total Price");
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(dto.getComputerId());
        cell = row.createCell(1);
        cell.setCellValue(dto.getComputerName());
        cell = row.createCell(2);
        cell.setCellValue(dto.getUsage());
        cell = row.createCell(3);
        cell.setCellValue(dto.getItems().size());
        cell = row.createCell(4);
        cell.setCellValue(sdf.format(dto.getWarranty()));
        cell = row.createCell(5);
        cell.setCellValue(dto.getTotalPrice().doubleValue() + " ₿");

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("Items Of Computer:");
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Serial Num.");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(1);
        cell.setCellValue("Component");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(2);
        cell.setCellValue("Component Type");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(3);
        cell.setCellValue("Price");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(4);
        cell.setCellValue("Amount");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(5);
        cell.setCellValue("Item Price");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(6);
        cell.setCellValue("Created Date");
        cell.setCellStyle(headerCellStyle);
        cell = row.createCell(7);
        cell.setCellValue("Created By User");
        cell.setCellStyle(headerCellStyle);

        for (int i = 0; i < dto.getItems().size(); i++) {
            row = sheet.createRow(i + 4);
            row.createCell(0).setCellValue(dto.getItems().get(i).getSerialNumber());
            row.createCell(1).setCellValue(dto.getItems().get(i).getComponent().getComponentName());
            row.createCell(2).setCellValue(dto.getItems().get(i).getComponent().getComponentType().getTypeName());
            row.createCell(3).setCellValue(dto.getItems().get(i).getItemPrice().doubleValue() + " ₿");
            row.createCell(4).setCellValue(dto.getItems().get(i).getAmount() + " pcs");
            row.createCell(5).setCellValue(dto.getItems().get(i).getTotalItemPrice().doubleValue() + " ₿");
            row.createCell(6).setCellValue(sdf.format(dto.getItems().get(i).getCreatedDate()));
            row.createCell(7).setCellValue(dto.getItems().get(i).getCreatedBy().getName() + " "
                    + dto.getItems().get(i).getCreatedBy().getSurname() + " ("
                    + dto.getItems().get(i).getCreatedBy().getUsername() + ")");
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}

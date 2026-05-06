package Util;
import java.util.Iterator;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DTOs.RegisterUser;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelReader {

    public List<Pair<RegisterUser, String>> readRegisterUsers(String fileName) {
        List<Pair<RegisterUser, String>> users = new ArrayList<>();

        try (InputStream is = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(fileName);
            Workbook workbook = new XSSFWorkbook(is)) {

            if (is == null) {
                throw new RuntimeException("File not found in resources: " + fileName);
            }

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= 7; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String firstName = getCellValue(row.getCell(0));
                String lastName = getCellValue(row.getCell(1));
                String baseEmail = getCellValue(row.getCell(2));
                String email = (baseEmail != null && !baseEmail.isEmpty()) ? baseEmail.replace("@", System.currentTimeMillis() + "@") : "";
                String telephone = getCellValue(row.getCell(3));
                String password = getCellValue(row.getCell(4));
                String confirmPassword = getCellValue(row.getCell(5));

                String click = getCellValue(row.getCell(6));

                System.out.println("Read user: " + email);

                users.add(new Pair<>(new RegisterUser(firstName, lastName, email, telephone, password, confirmPassword), click));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

}
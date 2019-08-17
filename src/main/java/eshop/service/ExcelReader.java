package eshop.service;

import eshop.model.Client;
import eshop.model.Product;
import eshop.model.enums.MeasureName;
import eshop.model.enums.ProductCategory;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static List<Client> importClientDataFromExcel(String excelPath) throws IOException {

        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        List<Client> clients = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 0) continue;
            Client client = extractClientFromExcelRow(row);
            clients.add(client);
        }
        workbook.close();
        return clients;
    }

    public static List<Product> importProductDataFromExcel(String excelPath) throws IOException {

        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        List<Product> products = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 0) continue;
            Product product = extractProductFromExcelRow(row);
            products.add(product);
        }
        workbook.close();
        return products;
    }

    private static Product extractProductFromExcelRow(Row row) {

        Long id = convertCellToLong(row.getCell(0));
        String name = convertCellToString(row.getCell(1));
        BigDecimal price = convertCellToBigDecimal(row.getCell(2));
        MeasureName measureName = MeasureName.valueOf(convertCellToString(row.getCell(3)).toUpperCase());
        ProductCategory productCategory = ProductCategory.valueOf(convertCellToString(row.getCell(4)).toUpperCase());

        return new Product(id, name, price, measureName, productCategory);
    }

    private static Client extractClientFromExcelRow(Row row) {

        Long id = convertCellToLong(row.getCell(0));
        String name = convertCellToString(row.getCell(1));
        String surname = convertCellToString(row.getCell(1));

        return new Client(id, name, surname);
    }

    private static Long convertCellToLong(Cell cell) {

        Long longCell = null;

        if (cell != null) {
            try {
                longCell = (long) Double.parseDouble(cell.toString());
            } catch (Exception e) {
                return null;
            }
        }
        return longCell;
    }

    private static BigDecimal convertCellToBigDecimal(Cell cell) {

        BigDecimal bigDecimalCell = null;

        if (cell != null) {
            try {
                bigDecimalCell = BigDecimal.valueOf(Double.parseDouble(cell.toString()));
            } catch (Exception e) {
                return null;
            }
        }
        return bigDecimalCell;
    }

    private static String convertCellToString(Cell cell) {

        String stringCell = null;

        if (cell != null) {
            try {
                stringCell = cell.toString();
            } catch (Exception e) {
                return null;
            }
        }
        return stringCell;
    }
}
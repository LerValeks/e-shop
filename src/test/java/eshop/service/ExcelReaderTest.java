package eshop.service;

import eshop.model.Client;
import eshop.model.Product;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExcelReaderTest {

    @Test
    public void importClientDataFromExcel() throws IOException {

        //given
        String excelPath = "C:\\Java Training\\e-shop\\src\\main\\java\\eshop\\Clients.xlsx";

        //when
        List<Client> exportedClients = ExcelReader.importClientDataFromExcel(excelPath);

        //then
        assertThat(exportedClients, hasSize(8));
    }

    @Test
    public void importProductDataFromExcel() throws IOException {

        //given
        String excelPath = "C:\\Java Training\\e-shop\\src\\main\\java\\eshop\\Products.xlsx";

        //when
        List<Product> exportedProducts = ExcelReader.importProductDataFromExcel(excelPath);

        //then
        assertThat(exportedProducts, hasSize(5));
    }
}
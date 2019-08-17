package eshop.service;

import eshop.model.Product;
import eshop.model.enums.MeasureName;
import eshop.model.enums.ProductCategory;
import eshop.service.exceptions.ProductException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductValidatorTest {

    private static Product createProduct() {

        return new Product("Product",
                BigDecimal.valueOf(100),
                MeasureName.PIECE,
                ProductCategory.ELECTRONICS);
    }

    @Test
    public void validateProduct_whenAllParametersGivenAndNotNull_shouldReturnTrue() throws ProductException {

        //given
        Product product = createProduct();

        //when
        boolean productValidation = ProductValidator.validateProduct(product);

        //then
        assertNotNull(productValidation);
        assertTrue(productValidation);
    }

    @Test
    public void validateProductPriceAmount_whenPriceBelowOrZero_shouldReturnTrue() throws ProductException {

        //given
        Product product = createProduct();
        product.setPrice(BigDecimal.valueOf(-5));

        //when
        boolean priceValidation = ProductValidator.validateProductPriceAmount(product);

        //then
        assertTrue(priceValidation);
    }
}
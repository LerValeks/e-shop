package eshop.service;

import eshop.model.Product;
import eshop.service.exceptions.ProductException;

import java.math.BigDecimal;

public class ProductValidator {

    public static boolean validateProduct(Product product) throws ProductException {

        if (product == null) {
            return false;
        }

        return validateProductName(product)
                && validateProductPrice(product)
                && validateProductMeasureName(product)
                && validateProductCategory(product);
    }

    public static boolean validateProductPriceAmount(Product product) throws ProductException {
        return product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
    }

    private static boolean validateProductName(Product product) {
        return product.getName() != null;
    }

    private static boolean validateProductMeasureName(Product product) {

        return product.getMeasureName() != null;
    }

    private static boolean validateProductPrice(Product product) {

        return product.getPrice() != null;
    }

    private static boolean validateProductCategory(Product product) {

        return product.getProductCategory() != null;
    }
}
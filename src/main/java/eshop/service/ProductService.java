package eshop.service;

import eshop.model.Product;
import eshop.model.enums.ProductCategory;
import eshop.service.exceptions.ProductException;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface ProductService {

    Product addProduct(Product product) throws ProductException;

    Product updateProduct(Product product) throws ProductException;

    Long removeProduct(Product product) throws ProductException;

    List<Product> filterProducts(List<Product> products, Predicate<Product> predicate);

    List<Product> findTopNumberOfProductsByCategory(List<Product> products,
                                                    Integer numberOfElements,
                                                    ProductCategory productCategory);

    Map<ProductCategory, List<Product>> groupByProductCategory(List<Product> products);
}
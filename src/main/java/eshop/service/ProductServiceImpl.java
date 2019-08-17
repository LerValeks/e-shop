package eshop.service;

import eshop.model.Product;
import eshop.model.enums.ProductCategory;
import eshop.repository.ProductRepository;
import eshop.service.exceptions.ProductException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) throws ProductException {

        if (!ProductValidator.validateProduct(product)) {
            throw new ProductException("Product has been incorrectly initiated or absent");
        }

        if (ProductValidator.validateProductPriceAmount(product)) {
            throw new ProductException("Product has price 0 or negative");
        }
        return productRepository.add(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductException {

        if (!ProductValidator.validateProduct(product)) {
            throw new ProductException("Product fields are incomplete");
        }

        if (ProductValidator.validateProductPriceAmount(product)) {
            throw new ProductException("Product has price 0 or negative");
        }
        return productRepository.updateDB(product);
    }

    @Override
    public Long removeProduct(Product product) throws ProductException {

        if (product == null) {
            throw new ProductException("Cannot remove null product");
        }
        return productRepository.removeFromDB(product);
    }

    @Override
    public List<Product> filterProducts(List<Product> products, Predicate<Product> predicate) {

        return products.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Product> findTopNumberOfProductsByCategory(List<Product> products, Integer numberOfElements, ProductCategory productCategory) {

        if (numberOfElements > products.size()) numberOfElements = products.size();

        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .filter(product -> product.getProductCategory().equals(productCategory))
                .limit(numberOfElements)
                .collect(toList());
    }

    @Override
    public Map<ProductCategory, List<Product>> groupByProductCategory(List<Product> products) {

        return products.stream()
                .collect(Collectors.groupingBy(Product::getProductCategory));
    }
}
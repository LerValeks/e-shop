package eshop.model;

import eshop.model.enums.MeasureName;
import eshop.model.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private MeasureName measureName;
    private ProductCategory productCategory;

    public Product(Long id, String name, BigDecimal price, MeasureName measureName, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.measureName = measureName;
        this.productCategory = productCategory;
    }

    public Product(String name,
                   BigDecimal price,
                   MeasureName measureName,
                   ProductCategory productCategory) {

        this.name = name;
        this.price = price;
        this.measureName = measureName;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MeasureName getMeasureName() {
        return measureName;
    }

    public void setMeasureName(MeasureName measureName) {
        this.measureName = measureName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                measureName == product.measureName &&
                productCategory == product.productCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, measureName, productCategory);
    }
}
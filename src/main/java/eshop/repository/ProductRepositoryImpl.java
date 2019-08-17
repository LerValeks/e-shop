package eshop.repository;

import eshop.model.Product;
import eshop.util.DBConnector;
import eshop.util.DBPropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class ProductRepositoryImpl implements ProductRepository {

    private Connection connection;

    public ProductRepositoryImpl() {
        init();
    }

    private static String buildAddQuery(String name, Double price, String measureName, String productCategory) {

        return new StringBuilder()
                .append("INSERT INTO product (name, price, measure_name, product_category) VALUES ('")
                .append(name)
                .append("', '")
                .append(price)
                .append("', '")
                .append(measureName)
                .append("', '")
                .append(productCategory)
                .append("')")
                .toString();
    }

    @Override
    public Product add(Product product) {

        String name = product.getName();
        Double price = product.getPrice().doubleValue();
        String measure = product.getMeasureName().toString();
        String category = product.getProductCategory().toString();

        try {
            Statement statement = connection.createStatement();
//            String addProductSQL = "INSERT INTO product (name, price, measure_name, product_category), VALUES (" +
//                    name + "," + price + "," + measure + "," + category + ")";
//            String addProductSQL = "INSERT INTO product(name) VALUES(" + "\"" + name + "\"" + ")";
            String addProductSQL = buildAddQuery(name, price, measure, category);
            statement.executeUpdate(addProductSQL);
        } catch (SQLException e) {
            //TODO add logger
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product updateDB(Product product) {
        return product;
    }

    @Override
    public Long removeFromDB(Product product) {
        return null;
    }

    public List<Product> retrieveFromDB() {
        return null;
    }

    private void init() {
        Properties properties = DBPropertiesReader.loadDBProperties();
        try {
            connection = DBConnector.createDbConnection(properties);
        } catch (SQLException e) {
            // TODO manage exception
        }
    }
}
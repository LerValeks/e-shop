package eshop.service;

import eshop.model.Basket;
import eshop.model.Client;
import eshop.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BasketService {

    Basket addProduct(Product product);

    Basket removeProduct(Product product);

    BigDecimal calculateClientBasket(Basket basket);

    Map<Product, Set<Client>> groupClientShoppingByProduct(List<Basket> baskets);
}
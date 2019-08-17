package eshop.service;

import eshop.model.Basket;
import eshop.model.Client;
import eshop.model.Product;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class BasketServiceImpl implements BasketService {

    private Basket basket;

    public BasketServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public Basket addProduct(Product product) {

        basket.getProducts().add(product);
        return basket;
    }

    @Override
    public Basket removeProduct(Product product) {

        basket.getProducts().remove(product);
        return basket;
    }

    @Override
    public BigDecimal calculateClientBasket(Basket basket) {

        BigDecimal basketPrice = BigDecimal.ZERO;
        List<Product> clientProducts = basket.getProducts();

        return clientProducts.stream()
                .map(Product::getPrice)
                .reduce(basketPrice, BigDecimal::add);
    }

    @Override
    public Map<Product, Set<Client>> groupClientShoppingByProduct(List<Basket> baskets) {

        //TODO:@Robert to advise why the second part doesn't use stream. Why we have .get(product) at comment in line 56

        Map<Product, Set<Client>> clientsGroupedByProducts = new HashMap<>();

        baskets.stream()
                .map(basket -> basket.getProducts())
                .flatMap(List<Product>::stream)
                .distinct()
                .collect(toList())
                .forEach(product -> clientsGroupedByProducts.put(product, new HashSet<>()));

        baskets.forEach(basket -> {
            basket.getProducts()
                    .forEach(product -> {
                        if (clientsGroupedByProducts.containsKey(product)) {
                            clientsGroupedByProducts.get(product).add(basket.getClient());
                        }
                    });
        });
        return clientsGroupedByProducts;
    }
}
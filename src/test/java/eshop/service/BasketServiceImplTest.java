package eshop.service;

import eshop.model.Basket;
import eshop.model.Client;
import eshop.model.Product;
import eshop.model.enums.MeasureName;
import eshop.model.enums.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceImplTest {

    private static Product createProduct() {

        return new Product("Product",
                BigDecimal.valueOf(100),
                MeasureName.PIECE,
                ProductCategory.ELECTRONICS);
    }

    private static List<Basket> createListOfBaskets(Integer numberOfBaskets, Integer productSetsPerBasket) {

        List<Basket> baskets = new ArrayList<>();

        for (int i = 0; i < numberOfBaskets; i++) {
            Client client = new Client("Client" + i, "Testing" + i, null);
            Basket basket = new Basket(client);
            BasketServiceImpl basketService = new BasketServiceImpl(basket);

            for (int j = 0; j < productSetsPerBasket; j++) {
                Product apple = new Product("Apple", BigDecimal.valueOf(1), MeasureName.KILOGRAM, ProductCategory.COFFEE);
                Product banana = new Product("Banana", BigDecimal.valueOf(10), MeasureName.GRAM, ProductCategory.ELECTRONICS);
                Product orange = new Product("Orange", BigDecimal.valueOf(100), MeasureName.PIECE, ProductCategory.TEA);

                basketService.addProduct(apple);
                basketService.addProduct(banana);
                basketService.addProduct(orange);
            }
            baskets.add(basket);
        }

        return baskets;
    }

    @Test
    public void addProduct_whenProductProvided_shouldAddToBasket() {

        //given
        Product product = createProduct();
        Client client = new Client("Client", "Testing", LocalDateTime.now());
        Basket basket = new Basket(client);
        BasketServiceImpl basketService = new BasketServiceImpl(basket);

        //when
        Basket fullBasket = basketService.addProduct(product);

        //then
        assertThat(fullBasket.getProducts().size(), is(1));
        assertThat(fullBasket.getProducts().get(0), equalTo(product));
    }

    @Test
    public void removeProduct_whenProductProvided_shouldRemoveFromBasket() {

        //given
        Product product = createProduct();
        Client client = new Client("Client", "Testing", LocalDateTime.now());
        Basket basket = new Basket(client);
        BasketServiceImpl basketService = new BasketServiceImpl(basket);

        //when
        Basket fullBasket = basketService.addProduct(product);
        basketService.removeProduct(product);

        //then
        assertThat(fullBasket.getProducts().size(), is(0));
    }

    @Test
    public void calculateClientBasket_whenMultipleProducts_shouldReturnSumOfProductsInBasket() {

        //given
        Client client = new Client("Client", "Testing", LocalDateTime.now());
        Basket basket = new Basket(client);
        BasketServiceImpl basketService = new BasketServiceImpl(basket);

        //when
        basketService.addProduct(createProduct());
        basketService.addProduct(createProduct()).getProducts().get(1).setPrice(BigDecimal.valueOf(55));

        BigDecimal basketValue = basketService.calculateClientBasket(basket);

        //then
        assertThat(basketValue, is(BigDecimal.valueOf(155)));
    }

    @Test
    public void groupClientShoppingByProduct() {

        //given
        List<Basket> baskets = createListOfBaskets(3, 1);
        BasketServiceImpl basketService = new BasketServiceImpl(null);

        //when
        Map<Product, Set<Client>> groupedMap = basketService.groupClientShoppingByProduct(baskets);
    }
}
package com.coffee.corner.stubs;

import com.coffee.corner.domain.MenuItem;
import com.coffee.corner.domain.Product;
import com.coffee.corner.exceptions.DataInitException;
import com.coffee.corner.repositories.MenuItemsRepository;
import com.coffee.corner.repositories.ProductsRepository;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.Optional;
import java.util.stream.Stream;

public class StubMenuItems {

  public static void fillProducts(final ProductsRepository productsRepository,
                           final MenuItemsRepository menuItemsRepository) {
    Stream<AbstractMap.SimpleEntry<Integer, Integer>> menuItemsStream = Stream.of(
        new AbstractMap.SimpleEntry<>(StubConstants.CoffeeIds.COFFEE_SMALL, 250),
        new AbstractMap.SimpleEntry<>(StubConstants.CoffeeIds.COFFEE_MEDIUM, 300),
        new AbstractMap.SimpleEntry<>(StubConstants.CoffeeIds.COFFEE_LARGE, 350),

        new AbstractMap.SimpleEntry<>(StubConstants.SnackIds.BACON_ROLL, 350),

        new AbstractMap.SimpleEntry<>(StubConstants.JuiceIds.FRESHLY_SQUEEZED_ORANGE_JUICE, 395),

        new AbstractMap.SimpleEntry<>(StubConstants.ExtraIds.EXTRA_MILK, 30),
        new AbstractMap.SimpleEntry<>(StubConstants.ExtraIds.FOAMED_MILK, 50),
        new AbstractMap.SimpleEntry<>(StubConstants.ExtraIds.SPECIAL_ROAST_COFFEE, 90)

    );
    menuItemsStream.forEach(kv -> {
      Optional<Product> optionalProduct = productsRepository.findById(kv.getKey());
      if (optionalProduct.isEmpty()) {
        throw new DataInitException(
            MessageFormat.format("Cannot find product with id {0} in productsRepository",
                kv.getKey()));
      }
      menuItemsRepository.add(kv.getKey(),
          MenuItem.builder()
              .id(kv.getKey())
              .product(optionalProduct.get())
              .price(kv.getValue())
          .build());
    });
  }

}

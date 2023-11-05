package com.coffee.corner.stubs;

import com.coffee.corner.domain.Product;
import com.coffee.corner.enums.ProductType;
import com.coffee.corner.repositories.ProductsRepository;

import java.util.stream.Stream;

public class StubProducts {

  private StubProducts() {}

  public static void fillProducts(ProductsRepository repository) {
    Stream<Product> productStream = Stream.of(Product.builder()
            .id(StubConstants.CoffeeIds.COFFEE_SMALL)
            .name("Coffee small")
            .type(ProductType.BEVERAGE)
            .build(),
        Product.builder()
            .id(StubConstants.CoffeeIds.COFFEE_MEDIUM)
            .name("Coffee medium,")
            .type(ProductType.BEVERAGE)
            .build(),
        Product.builder()
            .id(StubConstants.CoffeeIds.COFFEE_LARGE)
            .name("Coffee large")
            .type(ProductType.BEVERAGE)
            .build(),
        Product.builder()
            .id(StubConstants.SnackIds.BACON_ROLL)
            .name("Bacon Roll")
            .type(ProductType.SNACK)
            .build(),
        Product.builder()
            .id(StubConstants.JuiceIds.FRESHLY_SQUEEZED_ORANGE_JUICE)
            .name("Freshly squeezed orange juice")
            .type(ProductType.BEVERAGE)
            .build(),
        Product.builder()
            .id(StubConstants.ExtraIds.EXTRA_MILK)
            .name("Extra milk")
            .type(ProductType.EXTRA)
            .build(),
        Product.builder()
            .id(StubConstants.ExtraIds.FOAMED_MILK)
            .name("Foamed milk")
            .type(ProductType.EXTRA)
            .build(),
        Product.builder()
            .id(StubConstants.ExtraIds.SPECIAL_ROAST_COFFEE)
            .name("Special roast coffee")
            .type(ProductType.EXTRA)
            .build());

    productStream.forEach(item -> repository.add(item.getId(), item));
  }


}

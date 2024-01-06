package com.coffee.corner.repositories;

import com.coffee.corner.domain.Product;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryTest {

  @Test
  void addItemAndFind_ok() {
    // given
    ProductsRepository repository = new ProductsRepository();

    // when
    repository.add(1, Product.builder().id(1)
        .build());

    // then
    assertEquals(1, repository.size());

    Optional<Product> result = repository.findById(1);
    assertTrue(result.isPresent());
    assertEquals(1, result.get().getId());

  }
}
package com.coffee.corner.services.print;

import com.coffee.corner.domain.Customer;
import com.coffee.corner.domain.MenuItem;
import com.coffee.corner.domain.Product;
import com.coffee.corner.domain.Receipt;
import com.coffee.corner.enums.ProductType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class ReceiptPrintTestRun {
  ReceiptPrint receiptPrint = new ReceiptPrint();
  Customer customer = Customer.builder()
      .id(1)
      .name("main test customer")
      .build();


  @Test
  void receiptV1() {
    // given
    var givenReceipt = Receipt.builder()
        .id(10)
        .customer(customer)
        .dateTime(LocalDateTime.now())
        .menuItemList(List.of(MenuItem.builder()
            .id(1)
            .product(Product.builder().id(1)
                .name("Coffee long name")
                .type(ProductType.BEVERAGE)
                .build())
            .price(50)
            .build(),
            MenuItem.builder()
                .id(2)
                .product(Product.builder().id(1)
                    .name("Coffee long name # 2")
                    .type(ProductType.BEVERAGE)
                    .build())
                .price(1_230_21)
                .build()
            )
        )
        .build();

    // when
    receiptPrint.receiptV1(givenReceipt);

    // then

  }
}
package com.coffee.corner.services;

import com.coffee.corner.domain.Customer;
import com.coffee.corner.domain.MenuItem;
import com.coffee.corner.domain.Product;
import com.coffee.corner.domain.Receipt;
import com.coffee.corner.enums.ProductType;
import com.coffee.corner.repositories.ReceiptsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptsService {
  private final ReceiptsRepository receiptsRepository;

  public ReceiptsService(ReceiptsRepository receiptsRepository) {
    this.receiptsRepository = receiptsRepository;

  }

  public Receipt newReceipt(final Customer customer, final List<MenuItem> inputMenuItems) {
    Map<ProductType, Long> productTypeMap = groupByItems(inputMenuItems);
    // If a client orders lots of beverages.
    int beverageQty = productTypeMap.getOrDefault(ProductType.BEVERAGE, 0L).intValue();
    int snackQty = productTypeMap.getOrDefault(ProductType.SNACK, 0L).intValue();

    // If we have 14 beverage portions and the 5th portion is free, we skip first 10 portions
    // and work with last 4 portions.
    int beverageQtyRestFromReceipt = beverageQtyFromReceipts(customer) % AppContext.INSTANCE.getEachBeverage();

    int[] qtyForFreeSales = new int[2]; // 1 - beverageQtyForFree, 2 - extraQtyForFree  
    qtyForFreeSales[0] = (beverageQtyRestFromReceipt + beverageQty) / AppContext.INSTANCE.getEachBeverage();
    qtyForFreeSales[1] = Math.min(beverageQty, snackQty);

    List<MenuItem> resutlOrderList = new ArrayList<>();
    if (!AppContext.INSTANCE.isUseStampCard()) {
      resutlOrderList.addAll(inputMenuItems);
    } else {
      inputMenuItems.forEach(menuItem -> {
        MenuItem menuItemResult = null;
        if (menuItem.getProduct().isBeverage() && qtyForFreeSales[0] > 0) {
          menuItemResult = copyProductWithPriceZero(menuItem);
          qtyForFreeSales[0]--;
        } else if (menuItem.getProduct().isExtra() && qtyForFreeSales[1] > 0) {
          menuItemResult = copyProductWithPriceZero(menuItem);
          qtyForFreeSales[1]--;
        } else {
          menuItemResult = MenuItem.builder()
              .id(menuItem.getId())
              .price(menuItem.getPrice())
              .product(menuItem.getProduct())
              .build();
        }
        resutlOrderList.add(menuItemResult);
      });
    }

    return Receipt.builder()
        .id(this.receiptsRepository.size() + 1)
        .dateTime(LocalDateTime.now())
        .customer(customer)
        .menuItemList(resutlOrderList)
        .build();
  }

  private static MenuItem copyProductWithPriceZero(MenuItem menuItem) {
    return MenuItem.builder()
        .id(menuItem.getId())
        .price(0)
        .product(menuItem.getProduct())
        .build();
  }

  private Map<ProductType, Long> groupByItems(List<MenuItem> items) {
    return items.stream()
        .map(MenuItem::getProduct)
        .collect(Collectors.groupingBy(Product::getType,
            Collectors.counting()));
  }


  private int beverageQtyFromReceipts(final Customer customer) {
    return (int) receiptsRepository.getAll().stream().filter(item ->
          item.getCustomer().getId() == customer.getId()
        ).flatMap(items2 ->
            items2.getMenuItemList().stream()
                .filter(item3 -> item3.getProduct().isBeverage()))
        .count();
  }


}

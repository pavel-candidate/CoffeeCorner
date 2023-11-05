package com.coffee.corner.services;

import com.coffee.corner.domain.Customer;
import com.coffee.corner.domain.MenuItem;
import com.coffee.corner.domain.Receipt;
import com.coffee.corner.exceptions.DataInitException;
import com.coffee.corner.repositories.MenuItemsRepository;
import com.coffee.corner.repositories.ProductsRepository;
import com.coffee.corner.repositories.ReceiptsRepository;
import com.coffee.corner.stubs.StubConstants;
import com.coffee.corner.stubs.StubMenuItems;
import com.coffee.corner.stubs.StubProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptsServiceTest {

  static ProductsRepository productsRepository;
  static MenuItemsRepository menuItemsRepository;

  static ReceiptsRepository receiptsRepository;
  // ReceiptsService receiptsService;

  Customer customer = Customer.builder()
      .id(1)
      .name("main test customer")
      .build();

  @BeforeEach
  void beforeEach() {
    initProductAndMenuItemRepositoryFromStubs();
  }

  // Tests group I. tests with empty repository
  private static Stream<Arguments> newReceipt_empty_repo_no_free_products_Data() {
    return Stream.of(
        Arguments.of(createOneSnack()),
        Arguments.of(createOneExtra()),
        Arguments.of(createOneCoffeeAndExtraInList()),
        Arguments.of(createTwoCoffeeAndExtraInList()),
        Arguments.of(createOneCoffeeInList()));
  }
  @ParameterizedTest
  @MethodSource("newReceipt_empty_repo_no_free_products_Data")
  void newReceipt_empty_repo_no_free_products(List<Integer> inputIdsList) {
    // given
    ReceiptsService receiptsService = initReceiptServiceFromStubs();

    List<MenuItem> inputMenuItemsList = getMenuItemsFromIdList(inputIdsList);

    // when
    Receipt receiptResult = receiptsService.newReceipt(customer, inputMenuItemsList);

    // then
    assertNotNull(receiptResult);
    assertNotNull(receiptResult.getMenuItemList());
    assertNotNull(receiptResult.getCustomer());

    assertEquals(inputMenuItemsList.size(), receiptResult.getMenuItemList().size());

    // assertEquals(createOneCoffeeInList().get(0), receiptResult.getMenuItemList().get(0));
  }



  private static Stream<Arguments> newReceipt_empty_repo_with_free_beverage_Data() {
    int qtyWithOneFreeBeverage = AppContext.INSTANCE.getEachBeverage();
    return Stream.of(
        Arguments.of(createCoffeePortionsForCoffeeFree(qtyWithOneFreeBeverage)),
        Arguments.of(createCoffeePortionsForCoffeeFree(qtyWithOneFreeBeverage + 1)),
        Arguments.of(createCoffeePortionsForCoffeeFree(qtyWithOneFreeBeverage + 2)));
  }
  @ParameterizedTest
  @MethodSource("newReceipt_empty_repo_with_free_beverage_Data")
  void newReceipt_empty_repo_with_free_beverage(List<Integer> inputIdsList) {
    // given
    ReceiptsService receiptsService = initReceiptServiceFromStubs();

    List<MenuItem> inputMenuItemsList = getMenuItemsFromIdList(inputIdsList);

    // when
    Receipt receiptResult = receiptsService.newReceipt(customer, inputMenuItemsList);

    // then
    assertNotNull(receiptResult);
    assertNotNull(receiptResult.getMenuItemList());
    assertNotNull(receiptResult.getCustomer());

    assertEquals(inputMenuItemsList.size(), receiptResult.getMenuItemList().size());

    //Looking for beverage with zero price
    List<MenuItem> resultMenuItemsWithZeroPrice = receiptResult.getMenuItemList().stream()
        .filter(item -> item.getProduct().isBeverage())
        .filter(item -> item.getPrice() == 0)
        .collect(Collectors.toList());
    assertEquals(1, resultMenuItemsWithZeroPrice.size());
  }

  // Tests group II. tests with filled repository
  private static Stream<Arguments> newReceipt_filled_repo_with_free_product_Data() {
    return Stream.of(
        Arguments.of(createCoffeePortionsForCoffeeFree(1)));
  }

  @ParameterizedTest
  @MethodSource("newReceipt_filled_repo_with_free_product_Data")
  void newReceipt_filled_repo_with_free_product(List<Integer> inputIdsList) {
    // given
    receiptsRepository = fillReceiptsRepository(AppContext.INSTANCE.getEachBeverage() - 1);
    ReceiptsService receiptsService = initReceiptServiceFromStubs(receiptsRepository);

    List<MenuItem> inputMenuItemsList = getMenuItemsFromIdList(inputIdsList);

    // when
    Receipt receiptResult = receiptsService.newReceipt(customer, inputMenuItemsList);

    // then
    assertNotNull(receiptResult);
    assertNotNull(receiptResult.getMenuItemList());
    assertNotNull(receiptResult.getCustomer());

    assertEquals(inputMenuItemsList.size(), receiptResult.getMenuItemList().size());

    //Looking for beverage with zero price
    List<MenuItem> resultMenuItemsWithZeroPrice = receiptResult.getMenuItemList().stream()
        .filter(item -> item.getProduct().isBeverage())
        .filter(item -> item.getPrice() == 0)
        .collect(Collectors.toList());
    assertEquals(1, resultMenuItemsWithZeroPrice.size());
  }

  // Test group III. Other test cases
  private static Stream<Arguments> newReceipt_with_beverage_and_snack_Data() {
    return Stream.of(
        Arguments.of(createBeverageAndSnackAndExtra(), true),
        Arguments.of(createBeverageAndSnackOnly(), false));
  }

  // all cases for this test with empty receipt repository.
  @ParameterizedTest
  @MethodSource("newReceipt_with_beverage_and_snack_Data")
  void newReceipt_with_beverage_and_snack(List<Integer> inputIdsList, boolean extraInputExists) {
    // given
    ReceiptsService receiptsService = initReceiptServiceFromStubs();

    List<MenuItem> inputMenuItemsList = getMenuItemsFromIdList(inputIdsList);

    // when
    Receipt receiptResult = receiptsService.newReceipt(customer, inputMenuItemsList);

    // then
    assertNotNull(receiptResult);
    assertNotNull(receiptResult.getMenuItemList());
    assertNotNull(receiptResult.getCustomer());

    assertEquals(inputMenuItemsList.size(), receiptResult.getMenuItemList().size());

    //Looking for beverage with zero price
    List<MenuItem> resultMenuItemsWithZeroPrice = receiptResult.getMenuItemList().stream()
        .filter(item -> item.getProduct().isExtra())
        .filter(item -> item.getPrice() == 0)
        .collect(Collectors.toList());
    if (extraInputExists) {

    }
    int expectedCount = extraInputExists ? 1 : 0;
    assertEquals(expectedCount, resultMenuItemsWithZeroPrice.size());


  }


  private ReceiptsRepository fillReceiptsRepository(int qtyOfBeverages) {
    List<Integer> oneProductIdList = List.of(StubConstants.CoffeeIds.COFFEE_LARGE);
    List<Receipt> inputList = IntStream.rangeClosed(1, qtyOfBeverages)
        .mapToObj(i ->
            Receipt.builder()
                .id(i)
                .dateTime(LocalDateTime.now())
                .customer(customer)
                .menuItemList(
                    oneProductIdList.stream().map(id ->
                        menuItemsRepository.findById(id)
                            .orElseThrow(() -> new DataInitException("cannot find " + id))
                    ).collect(Collectors.toList()))
                .build()
        ).collect(Collectors.toList());

    ReceiptsRepository toReturn = new ReceiptsRepository();
    inputList.forEach(kv -> toReturn.add(kv.getId(), kv));
    return toReturn;
  }


  private static List<MenuItem> getMenuItemsFromIdList(List<Integer> inputIdsList) {
    List<MenuItem> inputMenuItemsList = inputIdsList.stream().map(id ->
        menuItemsRepository.findById(id)
            .orElseThrow(() -> new DataInitException("cannot find " + id))
    ).collect(Collectors.toList());
    return inputMenuItemsList;
  }

  private static List<Integer> createOneCoffeeInList() {
    return List.of(StubConstants.CoffeeIds.COFFEE_SMALL);
  }
  private static List<Integer> createOneSnack() {
    return List.of(StubConstants.SnackIds.BACON_ROLL);
  }
  private static List<Integer> createOneExtra() {
    return List.of(StubConstants.ExtraIds.EXTRA_MILK);
  }

  private static List<Integer> createOneCoffeeAndExtraInList() {
    return List.of(StubConstants.CoffeeIds.COFFEE_SMALL, StubConstants.ExtraIds.EXTRA_MILK);
  }

  private static List<Integer> createTwoCoffeeAndExtraInList() {
    return List.of(StubConstants.CoffeeIds.COFFEE_SMALL,
        StubConstants.ExtraIds.EXTRA_MILK);
  }

  private static List<Integer> createBeverageAndSnackOnly() {
    return List.of(StubConstants.CoffeeIds.COFFEE_MEDIUM, StubConstants.SnackIds.BACON_ROLL);
  }

  private static List<Integer> createBeverageAndSnackAndExtra() {
    return List.of(StubConstants.CoffeeIds.COFFEE_MEDIUM, StubConstants.SnackIds.BACON_ROLL, StubConstants.ExtraIds.EXTRA_MILK);
  }

  private static List<Integer> createCoffeePortionsForCoffeeFree(int qty) {
    var productId = StubConstants.CoffeeIds.COFFEE_LARGE;
    return IntStream.rangeClosed(1, qty)
        .mapToObj(i -> productId).collect(Collectors.toList());
  }


  private static void initProductAndMenuItemRepositoryFromStubs() {
    productsRepository = new ProductsRepository();
    StubProducts.fillProducts(productsRepository);
    menuItemsRepository = new MenuItemsRepository();
    StubMenuItems.fillProducts(productsRepository, menuItemsRepository);
  }

  private static ReceiptsService initReceiptServiceFromStubs() {
    receiptsRepository = new ReceiptsRepository();
    return initReceiptServiceFromStubs(receiptsRepository);
  }

  private static ReceiptsService initReceiptServiceFromStubs(final ReceiptsRepository receiptsRepository) {
    return new ReceiptsService(receiptsRepository);
  }

}
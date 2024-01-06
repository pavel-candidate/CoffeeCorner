package com.coffee.corner.stubs;

public class StubConstants {

  private StubConstants() {}

  /* These ids for tests purposes are using for products and menu items.
   * ie it of product equals to menu item. It's not mandatory.
   */
  public interface CoffeeIds {
    int COFFEE_SMALL = 1;
    int COFFEE_MEDIUM = 2;
    int COFFEE_LARGE = 3;

  }

  public interface JuiceIds {
    int FRESHLY_SQUEEZED_ORANGE_JUICE = 6;
  }

  public interface SnackIds {
    int BACON_ROLL = 20;
  }

  public interface ExtraIds {
    int EXTRA_MILK = 50;
    int FOAMED_MILK = 51;
    int SPECIAL_ROAST_COFFEE = 52;
  }

}

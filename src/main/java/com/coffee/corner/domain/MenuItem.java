package com.coffee.corner.domain;

public final class MenuItem {
  private final int id;
  private final Product product;

  private final int price;

  MenuItem(int id, Product product, int price) {
    this.id = id;
    this.product = product;
    this.price = price;
  }

  public static MenuItemBuilder builder() {return new MenuItemBuilder();}


  public int getId() {return this.id;}

  public Product getProduct() {return this.product;}

  public int getPrice() {return this.price;}

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof MenuItem)) return false;
    final MenuItem other = (MenuItem) o;
    if (this.getId() != other.getId()) return false;
    final Object this$product = this.getProduct();
    final Object other$product = other.getProduct();
    if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
    if (this.getPrice() != other.getPrice()) return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $product = this.getProduct();
    result = result * PRIME + ($product == null ? 43 : $product.hashCode());
    result = result * PRIME + this.getPrice();
    return result;
  }

  public String toString() {return "MenuItem(id=" + this.getId() + ", product=" + this.getProduct() + ", price=" + this.getPrice() + ")";}

  public static class MenuItemBuilder {
    private int id;
    private Product product;
    private int price;

    MenuItemBuilder() {}

    public MenuItemBuilder id(int id) {
      this.id = id;
      return this;
    }

    public MenuItemBuilder product(Product product) {
      this.product = product;
      return this;
    }

    public MenuItemBuilder price(int price) {
      this.price = price;
      return this;
    }

    public MenuItem build() {
      return new MenuItem(id, product, price);
    }

    public String toString() {return "MenuItem.MenuItemBuilder(id=" + this.id + ", product=" + this.product + ", price=" + this.price + ")";}
  }
}

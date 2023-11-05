package com.coffee.corner.domain;

import com.coffee.corner.enums.ProductType;

public final class Product {
  private final int id;
  private final String name;
  private final ProductType type;

  Product(int id, String name, ProductType type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public static ProductBuilder builder() {return new ProductBuilder();}

  public boolean isBeverage() {
    return this.type == ProductType.BEVERAGE;
  }

  public boolean isSnack() {
    return this.type == ProductType.SNACK;
  }

  public boolean isExtra() {
    return this.type == ProductType.EXTRA;
  }

  public int getId() {return this.id;}

  public String getName() {return this.name;}

  public ProductType getType() {return this.type;}

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Product)) return false;
    final Product other = (Product) o;
    if (this.getId() != other.getId()) return false;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    final Object this$type = this.getType();
    final Object other$type = other.getType();
    if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $type = this.getType();
    result = result * PRIME + ($type == null ? 43 : $type.hashCode());
    return result;
  }

  public String toString() {return "Product(id=" + this.getId() + ", name=" + this.getName() + ", type=" + this.getType() + ")";}

  public static class ProductBuilder {
    private int id;
    private String name;
    private ProductType type;

    ProductBuilder() {}

    public ProductBuilder id(int id) {
      this.id = id;
      return this;
    }

    public ProductBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ProductBuilder type(ProductType type) {
      this.type = type;
      return this;
    }

    public Product build() {
      return new Product(id, name, type);
    }

    public String toString() {return "Product.ProductBuilder(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ")";}
  }
}

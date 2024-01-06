package com.coffee.corner.domain;

import java.text.MessageFormat;

public final class Customer {
  private final int id;
  private final String name;

  Customer(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static CustomerBuilder builder() {return new CustomerBuilder();}

  public int getId() {return this.id;}

  public String getName() {return this.name;}

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Customer)) return false;
    final Customer other = (Customer) o;
    if (this.getId() != other.getId()) return false;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    return result;
  }

  public String toString() {return "Customer(id=" + this.getId() + ", name=" + this.getName() + ")";}

  public static class CustomerBuilder {
    private int id;
    private String name;

    CustomerBuilder() {}

    public CustomerBuilder id(int id) {
      this.id = id;
      return this;
    }

    public CustomerBuilder name(String name) {
      this.name = name;
      return this;
    }

    public Customer build() {
      return new Customer(id, name);
    }

    public String toString() {
      return MessageFormat.format("Customer.CustomerBuilder(id={0}, name={1})",
          this.id, this.name);
    }
  }
}

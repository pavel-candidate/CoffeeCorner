package com.coffee.corner.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public final class Receipt {
  private final Integer id;
  private final LocalDateTime dateTime;
  private final Customer customer;
  private final String comment;
  private final List<MenuItem> menuItemList;
  private final String attractingMessage;

  Receipt(Integer id, LocalDateTime dateTime, Customer customer, String comment, List<MenuItem> menuItemList, String attractingMessage) {
    this.id = id;
    this.dateTime = dateTime;
    this.customer = customer;
    this.comment = comment;
    this.menuItemList = menuItemList;
    this.attractingMessage = attractingMessage;
  }

  public static ReceiptBuilder builder() {return new ReceiptBuilder();}

  public Integer getId() {return this.id;}

  public LocalDateTime getDateTime() {return this.dateTime;}

  public Customer getCustomer() {return this.customer;}

  public String getComment() {return this.comment;}

  public List<MenuItem> getMenuItemList() {return Collections.unmodifiableList(this.menuItemList);}

  public String getAttractingMessage() {return this.attractingMessage;}

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Receipt)) return false;
    final Receipt other = (Receipt) o;
    final Object this$id = this.getId();
    final Object other$id = other.getId();
    if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
    final Object this$dateTime = this.getDateTime();
    final Object other$dateTime = other.getDateTime();
    if (this$dateTime == null ? other$dateTime != null : !this$dateTime.equals(other$dateTime)) return false;
    final Object this$customer = this.getCustomer();
    final Object other$customer = other.getCustomer();
    if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
    final Object this$comment = this.getComment();
    final Object other$comment = other.getComment();
    if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) return false;
    final Object this$menuItemList = this.getMenuItemList();
    final Object other$menuItemList = other.getMenuItemList();
    if (this$menuItemList == null ? other$menuItemList != null : !this$menuItemList.equals(other$menuItemList))
      return false;
    final Object this$attractingMessage = this.getAttractingMessage();
    final Object other$attractingMessage = other.getAttractingMessage();
    if (this$attractingMessage == null ? other$attractingMessage != null : !this$attractingMessage.equals(other$attractingMessage))
      return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $id = this.getId();
    result = result * PRIME + ($id == null ? 43 : $id.hashCode());
    final Object $dateTime = this.getDateTime();
    result = result * PRIME + ($dateTime == null ? 43 : $dateTime.hashCode());
    final Object $customer = this.getCustomer();
    result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
    final Object $comment = this.getComment();
    result = result * PRIME + ($comment == null ? 43 : $comment.hashCode());
    final Object $menuItemList = this.getMenuItemList();
    result = result * PRIME + ($menuItemList == null ? 43 : $menuItemList.hashCode());
    final Object $attractingMessage = this.getAttractingMessage();
    result = result * PRIME + ($attractingMessage == null ? 43 : $attractingMessage.hashCode());
    return result;
  }

  public String toString() {return "Receipt(id=" + this.getId() + ", dateTime=" + this.getDateTime() + ", customer=" + this.getCustomer() + ", comment=" + this.getComment() + ", menuItemList=" + this.getMenuItemList() + ", attractingMessage=" + this.getAttractingMessage() + ")";}

  public static class ReceiptBuilder {
    private Integer id;
    private LocalDateTime dateTime;
    private Customer customer;
    private String comment;
    private List<MenuItem> menuItemList;
    private String attractingMessage;

    ReceiptBuilder() {}

    public ReceiptBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public ReceiptBuilder dateTime(LocalDateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    public ReceiptBuilder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public ReceiptBuilder comment(String comment) {
      this.comment = comment;
      return this;
    }

    public ReceiptBuilder menuItemList(List<MenuItem> menuItemList) {
      this.menuItemList = menuItemList;
      return this;
    }

    public ReceiptBuilder attractingMessage(String attractingMessage) {
      this.attractingMessage = attractingMessage;
      return this;
    }

    public Receipt build() {
      return new Receipt(id, dateTime, customer, comment, menuItemList, attractingMessage);
    }

    public String toString() {return "Receipt.ReceiptBuilder(id=" + this.id + ", dateTime=" + this.dateTime + ", customer=" + this.customer + ", comment=" + this.comment + ", menuItemList=" + this.menuItemList + ", attractingMessage=" + this.attractingMessage + ")";}
  }
}

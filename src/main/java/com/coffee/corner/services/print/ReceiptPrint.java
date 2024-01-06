package com.coffee.corner.services.print;

import com.coffee.corner.domain.Receipt;
import com.coffee.corner.services.AppContext;
import com.coffee.corner.utils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ReceiptPrint {

  public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

  public void receiptV1(Receipt receipt) {
    final String currency = AppContext.INSTANCE.getCurrency();
    System.out.print("Receipt. # " + receipt.getId());
    System.out.println(" Customer " +
        StringUtils.strFixedLength(receipt.getCustomer().getName(), 20));
    System.out.println(LocalDateTime.now().format(dateTimeFormatter));

    final int[] totalSum = new int[1];
    totalSum[0] = 0;
    receipt.getMenuItemList().forEach(item -> {
      System.out.print(StringUtils.strFixedLength(item.getProduct().getName(), 17));
      System.out.print(StringUtils.formatDoubleWith2Decimals((double)item.getPrice() / 100));
      System.out.println(" " + currency);
      totalSum[0] += item.getPrice();
    });
    System.out.println("=".repeat(30));
    System.out.print("Total sum :" + StringUtils.formatDoubleWith2Decimals((double)totalSum[0] / 100));
    System.out.println(" " + currency);
  }


}

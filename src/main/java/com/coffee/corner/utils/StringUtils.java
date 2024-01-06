package com.coffee.corner.utils;

import java.text.DecimalFormat;

public class StringUtils {
private static final DecimalFormat df12poz = new DecimalFormat("###,##0.00");

  public static String strFixedLength(final String inputStr, final int length) {
    final int strLength = inputStr.length();
    if (strLength >= length) {
      return inputStr.substring(0, length);
    }
    return new StringBuilder(inputStr)
        .append(" ".repeat(length - strLength))
        .toString();
  }

  public static String formatDoubleWith2Decimals(double value) {
    return String.format("%12s", df12poz.format(value));
  }


}

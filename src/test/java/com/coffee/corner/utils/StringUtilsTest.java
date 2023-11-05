package com.coffee.corner.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class StringUtilsTest {

  private static Stream<Arguments> strFixedLength_Data() {
    return Stream.of(
        Arguments.of("123  ", "123"),
        Arguments.of("1234 ", "1234"),
        Arguments.of("12345", "12345"),
        Arguments.of("12345", "1234567"));
  }
  @ParameterizedTest
  @MethodSource("strFixedLength_Data")
  void strFixedLength(final String expected, final String input) {
    // given
    final int testingLength = 5;

    // when
    final String result = StringUtils.strFixedLength(input, testingLength);

    // then
    assertNotNull(result);
    assertEquals(expected, result);
    assertEquals(testingLength, result.length());
  }

  private static Stream<Arguments> formatDoubleWith2Decimals_Data() {
    return Stream.of(
        Arguments.of("   -1,000.34", -1_000.34),
        Arguments.of("    1,000.34", 1_000.34),
        Arguments.of("       10.22", 10.22));
  }
  @ParameterizedTest
  @MethodSource("formatDoubleWith2Decimals_Data")
  void formatDoubleWith2Decimals(final String expected, final double input) {
    // when
    String result = StringUtils.formatDoubleWith2Decimals(input);
    // then
    assertNotNull(result);
    assertEquals(expected, result);
  }


}
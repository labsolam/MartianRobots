package com.labsolam.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrientationTest {

  @ParameterizedTest
  @CsvSource({"N, W", "W, S", "S, E", "E, N"})
  void turnsLeftNinetyDegrees(Orientation from, Orientation expected) {
    assertThat(from.left()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({"N, E", "E, S", "S, W", "W, N"})
  void turnsRightNinetyDegrees(Orientation from, Orientation expected) {
    assertThat(from.right()).isEqualTo(expected);
  }
}

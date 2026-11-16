package com.labsolam.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

  @ParameterizedTest
  @CsvSource({"N, 2, 3", "E, 3, 2", "S, 2, 1", "W, 1, 2"})
  void movesOneGridPointInTheGivenDirection(Orientation direction, int expectedX, int expectedY) {
    assertThat(new Position(2, 2).move(direction)).isEqualTo(new Position(expectedX, expectedY));
  }
}

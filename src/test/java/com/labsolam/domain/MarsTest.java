package com.labsolam.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MarsTest {

  private final Mars mars = new Mars(5, 3);

  @ParameterizedTest
  @CsvSource({"0, 0", "5, 3", "0, 3", "5, 0", "2, 1"})
  void containsEveryPointFromTheOriginToTheUpperRightInclusive(int x, int y) {
    assertThat(mars.contains(new Position(x, y))).isTrue();
  }

  @ParameterizedTest
  @CsvSource({"-1, 0", "0, -1", "6, 3", "5, 4"})
  void doesNotContainPointsBeyondItsEdges(int x, int y) {
    assertThat(mars.contains(new Position(x, y))).isFalse();
  }

  @Test
  void zeroByZeroGridIsASingleValidPoint() {
    Mars tiny = new Mars(0, 0);

    assertThat(tiny.contains(new Position(0, 0))).isTrue();
    assertThat(tiny.contains(new Position(0, 1))).isFalse();
  }

  @Test
  void hasNoScentUntilARobotIsLost() {
    assertThat(mars.hasScent(new Position(5, 3), Orientation.N)).isFalse();
  }

  @Test
  void remembersScentForThePositionAndDirectionARobotWasLost() {
    mars.leaveScent(new Position(5, 3), Orientation.N);

    assertThat(mars.hasScent(new Position(5, 3), Orientation.N)).isTrue();
  }

  @Test
  void scentDoesNotCoverOtherDirectionsFromTheSamePoint() {
    mars.leaveScent(new Position(5, 3), Orientation.N);

    assertThat(mars.hasScent(new Position(5, 3), Orientation.E)).isFalse();
  }

  @Test
  void scentDoesNotCoverOtherPoints() {
    mars.leaveScent(new Position(5, 3), Orientation.N);

    assertThat(mars.hasScent(new Position(4, 3), Orientation.N)).isFalse();
  }
}

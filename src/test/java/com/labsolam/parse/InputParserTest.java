package com.labsolam.parse;

import static org.assertj.core.api.Assertions.assertThat;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Orientation;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;
import org.junit.jupiter.api.Test;

class InputParserTest {

  private final InputParser parser = new InputParser();

  @Test
  void parsesTheGridLineAsTheUpperRightCorner() {
    Mars mars = parser.grid("5 3");

    assertThat(mars.contains(new Position(5, 3))).isTrue();
    assertThat(mars.contains(new Position(6, 3))).isFalse();
    assertThat(mars.contains(new Position(5, 4))).isFalse();
  }

  @Test
  void parsesAPositionLineIntoARobot() {
    assertThat(parser.position("1 2 E"))
        .isEqualTo(RobotState.at(new Position(1, 2), Orientation.E));
  }

  @Test
  void separatesPartsByAnyWhitespace() {
    assertThat(parser.position("3\t2   N"))
        .isEqualTo(RobotState.at(new Position(3, 2), Orientation.N));
  }
}

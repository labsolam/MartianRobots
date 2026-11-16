package com.labsolam.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Orientation;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;
import org.junit.jupiter.api.Test;

class TurnCommandsTest {

  private final Mars mars = new Mars(5, 3);
  private final RobotState robot = RobotState.at(new Position(1, 1), Orientation.N);

  @Test
  void turnLeftRotatesTheRobotAndKeepsItOnTheSamePoint() {
    assertThat(new TurnLeft().apply(robot, mars))
        .isEqualTo(RobotState.at(new Position(1, 1), Orientation.W));
  }

  @Test
  void turnRightRotatesTheRobotAndKeepsItOnTheSamePoint() {
    assertThat(new TurnRight().apply(robot, mars))
        .isEqualTo(RobotState.at(new Position(1, 1), Orientation.E));
  }
}

package com.labsolam.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Orientation;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CommandsTest {

  private final Mars mars = new Mars(5, 3);
  private final Commands commands = Commands.standard();

  @Test
  void runsEachInstructionInOrder() {
    RobotState start = RobotState.at(new Position(0, 0), Orientation.S);

    assertThat(commands.execute(start, "RRF", mars))
        .isEqualTo(RobotState.at(new Position(0, 1), Orientation.N));
  }

  @Test
  void finishesOnTheStartingPointWithANewOrientationWhenGivenOnlyTurns() {
    RobotState start = RobotState.at(new Position(2, 2), Orientation.N);

    assertThat(commands.execute(start, "LLL", mars))
        .isEqualTo(RobotState.at(new Position(2, 2), Orientation.E));
  }

  @Test
  void skipsTheRemainingInstructionsOnceTheRobotIsLost() {
    RobotState start = RobotState.at(new Position(3, 3), Orientation.N);

    assertThat(commands.execute(start, "FRRF", mars))
        .isEqualTo(new RobotState(new Position(3, 3), Orientation.N, true));
  }

  @Test
  void withAddsACommandToTheStandardSet() {
    Command uTurn = (robot, m) -> robot.withOrientation(robot.orientation().left().left());
    Commands extended = Commands.standard().with('U', uTurn);

    assertThat(extended.execute(RobotState.at(new Position(4, 2), Orientation.S), "FU", mars))
        .isEqualTo(RobotState.at(new Position(4, 1), Orientation.N));
  }

  @Test
  void withLeavesTheOriginalCommandsUnchanged() {
    Command uTurn = (robot, m) -> robot.withOrientation(robot.orientation().left().left());
    Commands original = new Commands(Map.of('F', new MoveForward()));
    original.with('F', uTurn);

    assertThat(original.execute(RobotState.at(new Position(1, 1), Orientation.N), "F", mars))
        .isEqualTo(RobotState.at(new Position(1, 2), Orientation.N));
  }
}

package com.labsolam.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Orientation;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;
import org.junit.jupiter.api.Test;

class MoveForwardTest {

  private final Mars mars = new Mars(5, 3);
  private final MoveForward forward = new MoveForward();

  @Test
  void movesOnePointInTheDirectionItFaces() {
    RobotState robot = RobotState.at(new Position(1, 1), Orientation.E);

    assertThat(forward.apply(robot, mars))
        .isEqualTo(RobotState.at(new Position(2, 1), Orientation.E));
  }

  @Test
  void isLostAtItsLastGridPositionWhenMovingOffTheEdge() {
    RobotState robot = RobotState.at(new Position(3, 3), Orientation.N);

    assertThat(forward.apply(robot, mars)).isEqualTo(robot.asLost());
  }

  @Test
  void leavesScentWhereItWasLost() {
    forward.apply(RobotState.at(new Position(3, 3), Orientation.N), mars);

    assertThat(mars.hasScent(new Position(3, 3), Orientation.N)).isTrue();
  }

  @Test
  void ignoresAMoveOffTheEdgeWhenScentedInTheSameDirection() {
    mars.leaveScent(new Position(3, 3), Orientation.N);
    RobotState robot = RobotState.at(new Position(3, 3), Orientation.N);

    assertThat(forward.apply(robot, mars)).isEqualTo(robot);
  }

  @Test
  void isLostOffADifferentEdgeOfAScentedCornerAndScentLeftOnMars() {
    mars.leaveScent(new Position(5, 3), Orientation.N);
    RobotState robot = RobotState.at(new Position(5, 3), Orientation.E);

    assertThat(forward.apply(robot, mars)).isEqualTo(robot.asLost());
    assertThat(mars.hasScent(new Position(5, 3), Orientation.E)).isTrue();
  }

  @Test
  void movesNormallyFromAScentedPointWhenStayingOnTheGrid() {
    mars.leaveScent(new Position(3, 3), Orientation.N);
    RobotState robot = RobotState.at(new Position(3, 3), Orientation.S);

    assertThat(forward.apply(robot, mars))
        .isEqualTo(RobotState.at(new Position(3, 2), Orientation.S));
  }
}

package com.labsolam.domain.command;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;

public final class MoveForward implements Command {

  @Override
  public RobotState apply(RobotState robot, Mars mars) {
    Position next = robot.position().move(robot.orientation());
    if (mars.contains(next)) {
      return robot.withPosition(next);
    }
    if (mars.hasScent(robot.position(), robot.orientation())) {
      return robot;
    }
    mars.leaveScent(robot.position(), robot.orientation());
    return robot.asLost();
  }
}

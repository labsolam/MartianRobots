package com.labsolam.domain.command;

import com.labsolam.domain.Mars;
import com.labsolam.domain.RobotState;

public final class TurnLeft implements Command {

  @Override
  public RobotState apply(RobotState robot, Mars mars) {
    return robot.withOrientation(robot.orientation().left());
  }
}

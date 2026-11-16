package com.labsolam.domain.command;

import com.labsolam.domain.Mars;
import com.labsolam.domain.RobotState;

public interface Command {
  RobotState apply(RobotState robot, Mars mars);
}

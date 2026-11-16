package com.labsolam.domain;

public record RobotState(Position position, Orientation orientation, boolean lost) {

  public static RobotState at(Position position, Orientation orientation) {
    return new RobotState(position, orientation, false);
  }

  public RobotState withPosition(Position newPosition) {
    return new RobotState(newPosition, orientation, lost);
  }

  public RobotState withOrientation(Orientation newOrientation) {
    return new RobotState(position, newOrientation, lost);
  }

  public RobotState asLost() {
    return new RobotState(position, orientation, true);
  }
}

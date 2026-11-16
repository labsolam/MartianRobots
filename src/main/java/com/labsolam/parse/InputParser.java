package com.labsolam.parse;

import com.labsolam.domain.Mars;
import com.labsolam.domain.Orientation;
import com.labsolam.domain.Position;
import com.labsolam.domain.RobotState;

public class InputParser {

  public Mars grid(String line) {
    String[] parts = split(line);
    return new Mars(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
  }

  public RobotState position(String line) {
    String[] parts = split(line);
    Position position = new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    return RobotState.at(position, Orientation.valueOf(parts[2]));
  }

  private static String[] split(String line) {
    return line.split("\\s+");
  }
}

package com.labsolam.domain.command;

import com.labsolam.domain.Mars;
import com.labsolam.domain.RobotState;
import java.util.HashMap;
import java.util.Map;

/** Maps instruction characters to commands and runs a robot's instruction string. */
public final class Commands {

  private final Map<Character, Command> commands;

  public Commands(Map<Character, Command> commands) {
    this.commands = Map.copyOf(commands);
  }

  public static Commands standard() {
    return new Commands(
        Map.of(
            'L', new TurnLeft(),
            'R', new TurnRight(),
            'F', new MoveForward()));
  }

  public Commands with(char instruction, Command command) {
    Map<Character, Command> extended = new HashMap<>(commands);
    extended.put(instruction, command);
    return new Commands(extended);
  }

  public RobotState execute(RobotState start, String instructions, Mars mars) {
    RobotState state = start;
    for (char instruction : instructions.toCharArray()) {
      state = commands.get(instruction).apply(state, mars);
      if (state.lost()) {
        break;
      }
    }
    return state;
  }
}

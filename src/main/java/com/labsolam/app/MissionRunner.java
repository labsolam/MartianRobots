package com.labsolam.app;

import com.labsolam.domain.Mars;
import com.labsolam.domain.RobotState;
import com.labsolam.domain.command.Commands;
import com.labsolam.parse.InputParser;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Reads the grid, then each robot's position and instruction lines, and prints each robot's final
 * state.
 */
public class MissionRunner {

  private final InputParser parser = new InputParser();
  private final Commands commands;

  public MissionRunner() {
    this(Commands.standard());
  }

  public MissionRunner(Commands commands) {
    this.commands = commands;
  }

  public void run(BufferedReader in, PrintWriter out) {
    Iterator<String> lines = in.lines().iterator();

    Mars mars = parser.grid(lines.next());
    while (lines.hasNext()) {
      RobotState start = parser.position(lines.next());
      String instructions = lines.next();
      out.print(format(commands.execute(start, instructions, mars)) + "\n");
      out.flush();
    }
  }

  private static String format(RobotState robot) {
    String result = robot.position().x() + " " + robot.position().y() + " " + robot.orientation();
    return robot.lost() ? result + " LOST" : result;
  }
}

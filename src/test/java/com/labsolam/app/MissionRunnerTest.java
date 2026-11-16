package com.labsolam.app;

import static org.assertj.core.api.Assertions.assertThat;

import com.labsolam.domain.command.Command;
import com.labsolam.domain.command.Commands;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MissionRunnerTest {

  @Test
  void producesTheSampleOutputFromTheBrief() {
    String input =
        """
        5 3
        1 1 E
        RFRFRFRF
        3 2 N
        FRRFLLFFRRFLL
        0 3 W
        LLFFFLFLFL
        """;

    assertThat(run(input))
        .isEqualTo(
            """
            1 1 E
            3 3 N LOST
            2 3 S
            """);
  }

  @Nested
  class LostRobots {

    @Test
    void reportsTheLastGridPositionFollowedByLost() {
      assertThat(
              run(
                  """
                  5 3
                  0 0 S
                  F
                  """))
          .isEqualTo("0 0 S LOST\n");
    }

    @Test
    void ignoresInstructionsAfterBeingLost() {
      assertThat(
              run(
                  """
                  5 3
                  0 0 S
                  FLFLF
                  """))
          .isEqualTo("0 0 S LOST\n");
    }
  }

  @Nested
  class Scent {

    @Test
    void savesALaterRobotMovingOffTheSamePointInTheSameDirection() {
      assertThat(
              run(
                  """
                  5 3
                  5 3 N
                  F
                  5 3 N
                  FL
                  """))
          .isEqualTo(
              """
              5 3 N LOST
              5 3 W
              """);
    }

    @Test
    void doesNotSaveALaterRobotLeavingACornerInADifferentDirection() {
      assertThat(
              run(
                  """
                  5 3
                  5 3 N
                  F
                  5 3 E
                  F
                  5 3 E
                  F
                  """))
          .isEqualTo(
              """
              5 3 N LOST
              5 3 E LOST
              5 3 E
              """);
    }

    @Test
    void onAZeroByZeroGridTheFirstRobotIsLostAndTheNextIsSaved() {
      assertThat(
              run(
                  """
                  0 0
                  0 0 N
                  F
                  0 0 N
                  F
                  """))
          .isEqualTo(
              """
              0 0 N LOST
              0 0 N
              """);
    }
  }

  @Nested
  class CustomCommands {

    @Test
    void runsAMissionWithACommandAddedToTheStandardSet() {
      Command uTurn = (robot, mars) -> robot.withOrientation(robot.orientation().left().left());
      MissionRunner runner = new MissionRunner(Commands.standard().with('U', uTurn));
      StringWriter output = new StringWriter();

      runner.run(
          new BufferedReader(
              new StringReader(
                  """
                  5 3
                  3 2 N
                  FUF
                  """)),
          new PrintWriter(output));

      assertThat(output.toString()).isEqualTo("3 2 S\n");
    }
  }

  private static String run(String input) {
    StringWriter output = new StringWriter();
    new MissionRunner().run(new BufferedReader(new StringReader(input)), new PrintWriter(output));
    return output.toString();
  }
}

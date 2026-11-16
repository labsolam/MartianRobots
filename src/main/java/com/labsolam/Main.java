package com.labsolam;

import com.labsolam.app.MissionRunner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
  static void main() {
    new MissionRunner()
        .run(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));
  }
}

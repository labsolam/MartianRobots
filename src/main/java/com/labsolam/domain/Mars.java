package com.labsolam.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * The rectangular grid from (0, 0) to (maxX, maxY), both corners inclusive. Also remembers the
 * scent left by lost robots.
 */
public class Mars {

  private record Scent(Position position, Orientation orientation) {}

  private final int maxX;
  private final int maxY;
  private final Set<Scent> scents = new HashSet<>();

  public Mars(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public boolean contains(Position position) {
    return position.x() >= 0 && position.x() <= maxX && position.y() >= 0 && position.y() <= maxY;
  }

  public boolean hasScent(Position position, Orientation orientation) {
    return scents.contains(new Scent(position, orientation));
  }

  public void leaveScent(Position position, Orientation orientation) {
    scents.add(new Scent(position, orientation));
  }
}

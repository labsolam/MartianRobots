package com.labsolam.domain;

public enum Orientation {
  N(0, 1),
  E(1, 0),
  S(0, -1),
  W(-1, 0);

  private static final Orientation[] CLOCKWISE = values();

  private final int dx;
  private final int dy;

  Orientation(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public Orientation left() {
    return CLOCKWISE[(ordinal() + CLOCKWISE.length - 1) % CLOCKWISE.length];
  }

  public Orientation right() {
    return CLOCKWISE[(ordinal() + 1) % CLOCKWISE.length];
  }

  int dx() {
    return dx;
  }

  int dy() {
    return dy;
  }
}

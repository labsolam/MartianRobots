package com.labsolam.domain;

public record Position(int x, int y) {

  public Position move(Orientation direction) {
    return new Position(x + direction.dx(), y + direction.dy());
  }
}

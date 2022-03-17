package com.example

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

import scala.util.Random

case class Position(x: Int, y: Int)

case object Position {
  val Delta = 25
  val Width = 600
  val Height = 600

  def inbounds(pos: Position): Boolean =
    pos.x >= 0 && pos.x <= Width && pos.y >= 0 && pos.y <= Height

  def randomPosition(): Position =
    Position(Random.nextInt(23)*Delta, Random.nextInt(23)*Delta)

  def makeRectangle(position: Position, color: Color) = new Rectangle {
    x = position.x
    y = position.y
    width = Position.Delta
    height = Position.Delta
    fill = color
  }
}

object Direction extends Enumeration {
  type Direction = Value
  val Up, Down, Left, Right = Value
}
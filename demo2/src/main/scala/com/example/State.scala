package com.example

import com.example.State.initialSnake

import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

case class State(snake: List[Position], food: Position) {
  import Direction._

  def newState(dir: Direction): State = {

    val startingHeadPosition = snake.head
    val newHeadPosition = dir match {
      case Up =>    startingHeadPosition.copy(y = startingHeadPosition.y - Position.Delta)
      case Down =>  startingHeadPosition.copy(y = startingHeadPosition.y + Position.Delta)
      case Left =>  startingHeadPosition.copy(x = startingHeadPosition.x - Position.Delta)
      case Right => startingHeadPosition.copy(y = startingHeadPosition.x + Position.Delta)
      case _ =>     startingHeadPosition
    }

    val newSnake: List[Position] =
      if(!Position.inbounds(newHeadPosition) || snake.tail.contains(newHeadPosition))
        initialSnake
      else if(food == newHeadPosition)
        food :: snake
      else
        newHeadPosition :: snake.init

    val newFood: Position = if(food == newHeadPosition) State.randomFood() else food

    State(newSnake, newFood)
  }

  def rectangles: List[Rectangle] = Position.makeRectangle(food, Red) :: snake.map(Position.makeRectangle(_, Green))

}

case object State {
  // Initial arrangement of the snake
  val initialSnake = List(Position(250,200), Position(225, 200), Position(200, 200))

  def randomFood(): Position = Position.randomPosition()
}
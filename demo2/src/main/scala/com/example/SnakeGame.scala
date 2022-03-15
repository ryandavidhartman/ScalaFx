package com.example

import com.example.SnakeGame.Direction.Direction
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text

object SnakeGame extends JFXApp3 {
  // https://www.youtube.com/watch?v=sp6QO6eRRrg&ab_channel=RocktheJVM
  // 4.42 mark

  val Delta = 25.0
  val Width = 600
  val Height = 600

  case class Position(x: Double, y: Double)

  object Direction extends Enumeration {
    type Direction = Value
    val Up, Down, Left, Right = Value
  }

  // Initial arrangement of the snake
  val initialSnake = List(Position(250,200), Position(225, 200), Position(200, 200))

  case class State(snake: List[Position], food: Position) {
    import Direction._

    def newState(dir: Direction): State = {

      val startingHeadPosition = snake.head
      val newHeadPosition = dir match {
        case Up =>    startingHeadPosition.copy(y = startingHeadPosition.y - Delta)
        case Down =>  startingHeadPosition.copy(y = startingHeadPosition.y + Delta)
        case Left =>  startingHeadPosition.copy(x = startingHeadPosition.x - Delta)
        case Right => startingHeadPosition.copy(y = startingHeadPosition.x + Delta)
        case _ =>     startingHeadPosition
      }

      val newSnake: List[Position] = ???
      val newFood: Position = ???
      State(newSnake, newFood)
    }
  }

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      width = 600
      height = 600
      scene = new Scene {
        fill = White
      }
    }
  }
}

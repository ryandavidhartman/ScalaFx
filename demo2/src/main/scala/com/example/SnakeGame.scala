package com.example

import javafx.application.Platform
import scalafx.application.JFXApp3
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.paint.Color._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SnakeGame extends JFXApp3 {
  // https://www.youtube.com/watch?v=sp6QO6eRRrg&ab_channel=RocktheJVM


  def gameLoop(update: () => Unit): Unit = Future {
    update()
    Thread.sleep(1000/25 * 4)
  }.flatMap(_ => Future(gameLoop(update)))


  override def start(): Unit = {
    val state = ObjectProperty(State(State.initialSnake, State.randomFood()))
    val frame = IntegerProperty(0)
    val direction = ObjectProperty(Direction.Right)

    frame.onChange {
      state.update(state.value.newState(direction.value))
    }

    stage = new JFXApp3.PrimaryStage {
      width = 600
      height = 600
      scene = new Scene {
        fill = White
        content = state.value.rectangles
        onKeyPressed = key => key.getText match {
          case "w" => direction.value = Direction.Up
          case "a" => direction.value = Direction.Left
          case "d" => direction.value = Direction.Right
          case "s" => direction.value = Direction.Down
        }
        frame.onChange {
          Platform.runLater {
            () => {
              content = state.value.rectangles
            }
          }
        }
      }

      gameLoop(() => frame.update(frame.value + 1))
    }
  }
}

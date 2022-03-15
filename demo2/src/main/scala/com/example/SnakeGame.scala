package com.example

import scalafx.application.JFXApp3
import scalafx.beans.property.ObjectProperty
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object SnakeGame extends JFXApp3 {
  // https://www.youtube.com/watch?v=sp6QO6eRRrg&ab_channel=RocktheJVM
  // 9:06


  override def start(): Unit = {
    val state = ObjectProperty(State(State.initialSnake, State.randomFood()))

    stage = new JFXApp3.PrimaryStage {
      width = 600
      height = 600
      scene = new Scene {
        fill = White
        content = state.value.rectangles
      }
    }
  }
}

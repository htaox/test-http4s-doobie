package com.eztier.testhttp4sdoobie

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    Testhttp4sdoobieServer.stream[IO].compile.drain.as(ExitCode.Success)
}
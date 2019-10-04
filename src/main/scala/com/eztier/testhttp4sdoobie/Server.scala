package com.eztier.testhttp4sdoobie

import cats.effect._
import cats.syntax.all._

import domain.authors._

import infrastructure.repository.doobie.{DoobieAuthorRepositoryInterpreter}

object Server extends IOApp {
  
  def run(args: List[String]): IO[ExitCode] =
    args.headOption match {
      case Some(name) =>
        IO(println(s"Hello, $name.")).as(ExitCode.Success)
      case None =>
        IO(System.err.println("Usage: MyApp name")).as(ExitCode(2))
    }

}

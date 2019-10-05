package com.eztier.testhttp4sdoobie

import cats.effect._
import cats.syntax.all._
import com.eztier.testhttp4sdoobie.infrastructure.endpoint.AuthorEndpoints
import org.http4s.server.{Router, Server => H4Server}
import domain.authors._
import infrastructure.repository.doobie.DoobieAuthorRepositoryInterpreter

object Server extends IOApp {

  def createServer[F[_]: ContextShift: ConcurrentEffect: Timer]: Resource[F, H4Server[F]] =
    val httpApp = Router(
      "/authors" -> AuthorEndpoints.endpoints[F](authorService)
    ).orNotFound

    for {

    } yield server

  def run(args: List[String]): IO[ExitCode] =




    args.headOption match {
      case Some(name) =>
        IO(println(s"Hello, $name.")).as(ExitCode.Success)
      case None =>
        IO(System.err.println("Usage: MyApp name")).as(ExitCode(2))
    }

}

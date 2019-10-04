package com.eztier.testhttp4sdoobie.domain
package authors

import cats.Applicative
import cats.Functor

import cats.data.EitherT
import cats.implicits._

class AuthorValidationInterpreter[F[_]: Applicative](authorRepo: AuthorRepositoryAlgebra[F]) extends AuthorValidationAlgebra[F] {
  def doesNotExist(author: Author): EitherT[F, AuthorAlreadyExistsError, Unit] =
    authorRepo
      .findByEmail(author.email)
      .map(AuthorAlreadyExistsError)
      .toLeft(())

  def exists(authorId: Option[Long]): EitherT[F, AuthorNotFoundError.type, Unit] =
    authorId match {
      case Some(id) =>

        authorRepo.get(id)
          .toRight(AuthorNotFoundError)

          .void // Functor.void[A](fa: F[A])  Empty the fa of the values, preserving the structure
          
      case None =>
        // EitherT.leftT, EitherT.rightT is alias for EitherT.pure
        EitherT.left[Unit](AuthorNotFoundError.pure[F])
    }
}

object AuthorValidationInterpreter {
  def apply[F[_]: Applicative](repo: AuthorRepositoryAlgebra[F]): AuthorValidationAlgebra[F] =
    new AuthorValidationInterpreter[F](repo)
}
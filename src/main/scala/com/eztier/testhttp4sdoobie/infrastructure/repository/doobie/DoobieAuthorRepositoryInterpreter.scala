package com.eztier.testhttp4sdoobie.infrastructure.repository.doobie

import doobie._
import doobie.implicits._

import cats.data.OptionT
import cats.effect.Bracket
import cats.implicits._

import com.eztier.testhttp4sdoobie.domain.authors.{Author, AuthorRepositoryAlgebra}

private object AuthorSQL {

  def findOne(id: Long): Query0[Author] = sql"""
    SELECT first_name, last_name, email, phone, id
    FROM authors
    WHERE id = $id
  """.query

  def findByEmail(email: String): Query0[Author] = sql"""
    SELECT first_name, last_name, email, phone, id
    FROM authors
    WHERE email = $email
  """.query
}

// Kind projector
// EitherT[*[_], Int, *]    // equivalent to: type R[F[_], B] = EitherT[F, Int, B]
// Bracket extends MonadError.
class DoobieAuthorRepositoryInterpreter[F[_]: Bracket[*[_], Throwable]](val xa: Transactor[F])
extends AuthorRepositoryAlgebra[F]
with IdentityStore[F, Long, Author] { self =>
  import AuthorSQL._

  def findOne(id: Long): OptionT[F, Author] = OptionT(findOne(id).option.transact(xa))

  def findByEmail(email: String): OptionT[F, Author] =
    OptionT(findByEmail(email).option.transact(xa))
}

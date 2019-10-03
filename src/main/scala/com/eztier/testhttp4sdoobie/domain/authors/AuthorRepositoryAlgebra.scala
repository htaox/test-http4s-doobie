package com.eztier.testhttp4sdoobie.domain
package authors

import cats.data.OptionT

trait AuthorRepositoryAlgebra[F[_]] {
  def get(id: Long): OptionT[F, Author]

  def findByEmail(email: String): OptionT[F, Author]
}

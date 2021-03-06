/*
 * Copyright 2013 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.heikoseeberger

import scala.util.Properties

package object demoakka {

  type Traversable[+A] = scala.collection.immutable.Traversable[A]

  val Traversable = scala.collection.immutable.Traversable

  type Iterable[+A] = scala.collection.immutable.Iterable[A]

  val Iterable = scala.collection.immutable.Iterable

  type Seq[+A] = scala.collection.immutable.Seq[A]

  val Seq = scala.collection.immutable.Seq

  type IndexedSeq[+A] = scala.collection.immutable.IndexedSeq[A]

  val IndexedSeq = scala.collection.immutable.IndexedSeq

  val newLine = Properties.lineSeparator
}

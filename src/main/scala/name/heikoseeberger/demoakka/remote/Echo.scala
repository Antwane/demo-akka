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

package name.heikoseeberger.demoakka
package remote

import akka.actor.{ ActorIdentity, Actor, ActorSystem, Identify, Props }
import akka.actor.ActorDSL._
import scala.concurrent.duration._
import scala.util.Random

object EchoClientApp extends App {

  System.setProperty("akka.remote.netty.tcp.port", "0") // Use a random port for the client
  val hostName = args.headOption getOrElse sys.error("Missing argument for hostname!")

  implicit val system = ActorSystem("demo-system")
  val client =
    actor(new Act {
      import context.dispatcher
      val id = Random.nextInt()
      system.actorSelection(s"akka.tcp://demo-system@$hostName:2552/user/echo") ! Identify(id)
      become {
        case ActorIdentity(`id`, Some(echo)) =>
          context.system.scheduler.schedule(0 seconds, 5 seconds, echo, "Hello, world!")
        case message =>
          println(message)
      }
    })

  readLine(s"Hit ENTER to exit ...$newLine")
  system.shutdown()
  system.awaitTermination()
}

object EchoServerApp extends App {

  val hostname = args.headOption getOrElse sys.error("Missing argument for hostname!")
  System.setProperty("akka.remote.netty.tcp.hostname", hostname)

  val system = ActorSystem("demo-system")
  val echo = system.actorOf(Props(new Echo), "echo")

  system.awaitTermination()
}

class Echo extends Actor {

  override def receive: Receive = {
    case message => sender ! message
  }
}

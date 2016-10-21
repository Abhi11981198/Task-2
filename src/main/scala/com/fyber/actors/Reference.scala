package com.fyber.actors

import akka.actor.{ActorRef, ActorSystem, Props}
import com.fyber.nodes.{Delay, Echo, Noop, Reverse}

object Reference {
  val system = ActorSystem("eventSimulationSystem")
  val delayActor = system.actorOf(Props[Delay], "delayNode")
  val echoActor = system.actorOf(Props[Echo], "echoNode")
  val noopActor = system.actorOf(Props[Noop], "noopNode")
  val reverseActor = system.actorOf(Props[Reverse], "reverseNode")
  var inputLength = 0
  var outputLength = 0

  def get(name: String): ActorRef = {
    name match {
      case "delay" => delayActor
      case "echo" => echoActor
      case "noop" => noopActor
      case "reverse" => reverseActor
    }
  }

  def isLimitReached(length: Int): Boolean = {
    outputLength += length
    if (outputLength > inputLength * 16) true else false
  }
}

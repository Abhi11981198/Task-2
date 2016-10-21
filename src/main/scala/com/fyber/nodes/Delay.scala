package com.fyber.nodes

import akka.actor.Actor
import com.fyber.actors.Reference
import com.fyber.models.FlowDetail

class Delay extends Actor {
  var previousInput = "tbb"

  override def receive: Receive = {
    case flowDetail: FlowDetail => {
      val output = previousInput
      previousInput = flowDetail.input
      flowDetail.remNodes match {
        case Nil => {
          if(!Reference.isLimitReached(output.length))
            print(s"$output ")
        }
        case head :: tail => {
          Reference.get(head) ! FlowDetail(output, tail)
        }
      }
    }
  }
}

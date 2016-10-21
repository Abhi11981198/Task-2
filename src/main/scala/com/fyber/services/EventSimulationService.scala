package com.fyber.services

import com.fyber.actors.Reference
import com.fyber.models._

object EventSimulationService {
  def simulate(tasks: List[Task], links: List[Link], processes: List[Process]) = {
    if (tasks.isEmpty) {
      throw new Exception("There must be atleast one task in input file.")
    } else if (links.size != 1) {
      throw new Exception("There must be one link in input file.")
    } else if (processes.size != 1) {
      throw new Exception("There must be one process in input file.")
    }
    val linkSeq: List[String] = links.head.nodes
    //validate correct linking of nodes
    val taskNodes = tasks.map(_.nodeName)
    val isValid = linkSeq.forall(x => taskNodes.contains(x))
    if (!isValid) {
      throw new Exception("Can't link non existing node.")
    }

    val inputSeq = processes.head.inputs

    //set input length in accumulator actor.
    Reference.inputLength = inputSeq.mkString.length

    //start sending input to entry node.
    inputSeq.foreach { input =>
      Reference.get(linkSeq.head) ! FlowDetail(input, linkSeq.tail)
    }

    //drain nodes if delay node is there in network.
    if (linkSeq.contains("delay"))
      Reference.get(linkSeq.head) ! FlowDetail("dry", linkSeq.tail)

    Thread.sleep(1000L) //to finish the task on different node.
    Reference.system.terminate()
  }
}

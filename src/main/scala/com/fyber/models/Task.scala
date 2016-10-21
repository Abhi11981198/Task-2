package com.fyber.models

case class Task(nodeName: String, operation: String)

case class Link(nodes: List[String])

case class Process(inputs: Seq[String])

case class FlowDetail(input: String, remNodes: List[String])
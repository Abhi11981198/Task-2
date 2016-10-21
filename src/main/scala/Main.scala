import com.fyber.models.{Link, Task, Process}
import com.fyber.services.EventSimulationService

object Main {
  def main(args: Array[String]) {
    val inputPath = if (args.nonEmpty) {
      args(0)
    } else {
      throw new Exception("Please provide a valid file path.")
      System.exit(0)
    }

    val it = scala.io.Source.fromFile(inputPath.toString).getLines()
    val (tasks, links, processes) = parseInputFile(it)
    EventSimulationService.simulate(tasks, links, processes)
  }

  def parseInputFile(it: Iterator[String],
                     tasks: List[Task] = Nil,
                     links: List[Link] = Nil,
                     processes: List[Process] = Nil):
  (List[Task], List[Link], List[Process]) = {

    if (it.hasNext) {
      val eventsplited = it.next().split(" ").toList
      eventsplited.head match {
        case "task" =>
          val task = Task(eventsplited(1), eventsplited(2))
          parseInputFile(it, task :: tasks, links, processes)

        case "link" =>
          val link = Link(eventsplited.tail)
          parseInputFile(it, tasks, link :: links, processes)

        case "process" =>
          val process = Process(eventsplited.tail)
          parseInputFile(it, tasks, links, process :: processes)

      }
    } else {
      (tasks, links, processes)
    }
  }
}

# Task-2

### Assumption
  - Each statement containg details is space separated.
  - Input file contains one link statement.
  - Input file contains one process statement.
  - Input file contains atleast one task statement.
  - Output for a particular input is dropped if limit is reached (16 times the input length.)
  
### Requirement
  - Java 8+
  - sbt 13.8

### Installation

Install java and sbt, and run :

```sh
$ sbt clean compile
$ sbt "run <input file path>"
```
OR

```sh
$ sbt clean compile
$ sbt assembly
```
After assembly you will find FyberTask-2-assembly-1.0.jar in target/scala-2.11

```sh
$ java -jar <path to assembly jar> <input file path>
```

For example :
```sh
$ sbt clean compile
$ sbt assembly
$ java -jar target/scala-2.11/FyberTask-2-assembly-1.0.jar input.txt
```

>If a task has multiple input links, and several events arrive at the same time they should be concatenated into one event before feeding the task. Concatenating consist of appending the strings, in the order in which links were created.
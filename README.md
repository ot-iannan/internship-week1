Day 5 - Dockerfile

Dockerfile used

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY SchoolSystem.java .

RUN javac SchoolSystem.java

CMD \["java", "SchoolSystem"]



\Build command

docker build -t schoolsystem-app .



\ Run command

docker run schoolsystem-app



\### Input

No user input required - the program runs a fixed School system demo

(creates a Student and Teacher, calls introduce() on both).



\### Output

I am Ama, a student studying Computer Science.

I am Mr. Kofi, a teacher.



\### Notes

\- Base image eclipse-temurin:21-jdk was chosen since it already includes

&#x20; the JDK, avoiding the need to install Java manually inside the image.

\- RUN compiles the code once during the image build; CMD defines what runs

&#x20; each time a new container starts from this image.

## SOLID Principles Applied

- **S**RP - Each class has one job: Person/Student/Teacher hold data and identity,
  PayrollRunner only handles paying, SchoolSystem only wires things together.
- **O**CP - New Payable types (e.g. a Contractor class) can be added without changing
  any existing class.
- **L**SP - Student and Teacher can always be used wherever a Person is expected.
- **I**SP - pay() lives only in Payable, so Student is never forced to implement it.
- **D**IP - PayrollRunner depends on the Payable interface, not on Teacher directly -
  proven by testPayrollRunnerWorksWithAnyPayable, which pays an unrelated DummyPayable
  class with no changes to PayrollRunner.
\## Week 2, Day 1 - Capstone Scope \& Architecture



\### Language

Java



\### Scope

A command-line task tracker that lets a user:

\- Add a new task

\- List all tasks

\- Mark a task as complete

\- Delete a task



Tasks are stored locally and persist between runs.



\### Task Fields

Each task has:

\- id (int) - a unique number identifying the task

\- description (String) - what the task is

\- status (boolean) - false = not done, true = done



\### Storage Approach

Tasks are saved to a plain text file (tasks.txt) in the project folder.

Each line represents one task, in the format:

id|description|status



Example:

1|Finish README|false

2|Buy groceries|true



On startup, the app reads this file into memory. Every add, complete, or

delete action re-writes the file so changes are saved immediately.





\### Package Layout

\- Task.java - holds a single task's data (id, description, status)

\- TaskStorage.java - handles reading tasks from and writing tasks to tasks.txt

\- Main.java - the entry point; reads user commands and calls TaskStorage/Task

&#x20; accordingly



This keeps each class focused on one job: Task holds data, TaskStorage handles

file reading/writing, and Main handles the command-line interaction - following

the Single Responsibility Principle from Week 1's SOLID lesson.



\### Command Examples

java Main add "Finish README"

\-> Adds a new task with description "Finish README"



java Main list

\-> Prints all tasks, showing id, description, and status



java Main complete 1

\-> Marks task with id 1 as done



java Main delete 2

\-> Removes task with id 2



\### Architecture Sketch

User (command line)

&#x20;     |

&#x20;     v

&#x20;  Main.java  --- reads command, calls the right action

&#x20;     |

&#x20;     v

&#x20;TaskStorage.java --- reads/writes tasks.txt

&#x20;     |



&#x20;     v

&#x20;  Task.java --- represents one task's data

&#x20;     |

&#x20;     v

&#x20;  tasks.txt (persisted file)



\### Milestones

1\. Build Task class with fields and a way to display itself

2\. Build TaskStorage to read/write tasks.txt

3\. Build Main to handle "add" and "list" commands

4\. Add "complete" and "delete" commands

5\. Test all commands end-to-end, confirm tasks.txt persists correctly



\### Acceptance Criteria

\- Running "java Main add <description>" creates a new task and saves it

\- Running "java Main list" shows all current tasks with correct status

\- Running "java Main complete <id>" updates that task's status to done

\- Running "java Main delete <id>" removes that task

\- Tasks persist correctly across separate runs (data isn't lost when the

&#x20; program restarts)


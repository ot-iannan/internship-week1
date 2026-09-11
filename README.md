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
## Week 2, Day 4 - Refactoring Notes

During Day 4, the capstone was refactored to fix gaps found while adding tests:

- TaskStorage was not actually connected to Main.java, so tasks did not
  persist between runs despite the storage code existing. This was fixed
  by loading saved tasks on startup and saving after every add/complete.
- A leftover placeholder message in the list command was removed.
- Input validation was added to reject empty task descriptions.
- A "list pending" filter and priority-based sorting were added, completing
  the Day 3 requirements that had been missed.

Package boundaries remain clear:
- Task.java and TaskManager.java - domain logic (what a task is, how tasks behave)
- TaskStorage.java - persistence (reading/writing tasks.txt)
- Main.java - CLI (reading commands, printing output)

Automated tests (TaskManagerTest.java) cover add, complete, invalid id,
empty description validation, and filtering - all passing.





\## Week 2 Capstone - Java CLI



\### Installation

Requires Java 21+ installed. Verify with:

java -version



\### Build

cd WeekTwo/src

javac Task.java TaskManager.java TaskStorage.java Main.java



\### Usage

java Main help

java Main add "<description>" \[priority]

java Main list

java Main list pending

java Main complete <id>



\### Examples

java Main add "Finish README" high

java Main list

java Main complete 1



\### Troubleshooting

\- "Missing description" error: you forgot to include a description after "add"

\- "Invalid id" error: the complete command needs a number, not text

\- Tasks not saving: make sure you are running from the WeekTwo/src folder,

&#x20; since tasks.txt is created relative to where you run the command.







\## Week 2 Capstone - Go CLI



\### Installation

Requires Go installed. Verify with:

go version



\### Build

cd WeekTwo/src

go build -o taskcli taskcli.go



\### Usage

./taskcli help

./taskcli add "<description>"

./taskcli list

./taskcli list --verbose

./taskcli complete <id>



\### Examples

./taskcli add "Buy groceries"

./taskcli list

./taskcli complete 1



\### Troubleshooting

\- "No such file or directory" when running ./taskcli: make sure you ran

&#x20; "go build" first, and that you are in the same folder as the binary

\- Missing description error: the add command needs text after it in quotes.


## Week 2, Day 5 - Docker (Java CLI)

### Build
cd WeekTwo/src
docker build -t taskcli-java .

### Run with persistent storage
docker run -v "<your-path>/WeekTwo/src/data:/data" taskcli-java add "<description>" [priority]
docker run -v "<your-path>/WeekTwo/src/data:/data" taskcli-java list
docker run -v "<your-path>/WeekTwo/src/data:/data" taskcli-java complete <id>

Replace <your-path> with the full path to your project folder.

### How persistence works
The container's /app folder holds the compiled program. A separate folder,
/data, is where tasks.txt is saved - this folder is mounted to a real folder
on your computer using -v, so task data survives even if the container is
deleted and a new one is created from the same image.



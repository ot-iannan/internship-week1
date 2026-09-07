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


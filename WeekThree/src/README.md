//Day 1 - HTTP \& Client-Server Fundamentals

The request-response lifecycle



1. Client builds a request ( method +URL+ headers+ body).
2. Request travels over the internet to a server at a specific address and port.
3. Server processes it and builds a response.
4. Response (status code + header + body) travels back to the server or client and does what needs to be done.



//Request parts

\-Method: GET(read), POST(create), PUT/PATCH(update), DELETE(removes).

\-URL: path parameters that identify a specific resource;

query parameters add optional filters.

\-Headers: data about the request (format wanted, identify, etc).

\-Body: the actual data being sent (used with POST/PUT)



//Statelessness:

Each request is handled independently; the server does not remember previous requests. Anything that needs to persist (like login) must be sent again with every new request.



//Safety and idempotency

\-Safe: does not change server data (GET)

\-Idempotent: repeating it produces the same end result as doing it once. (PUT/DELETE)

\-POST is neither safe nor idempotent- repeating it creates new duplicates each time.





\## Day 1 - REST, JSON \& Status Codes



\### REST core idea

Resources are nouns (tasks, users), identified by URLs. HTTP methods

(GET/POST/PUT/DELETE) describe the action, not the URL itself.



\### JSON conventions used

\- A missing field means the field does not apply / was never set.

\- A null field means the field was explicitly cleared or has no value,

&#x20; but is still a recognized field for this resource.

\- Every response uses the same field names and structure for the same

&#x20; resource type, regardless of success or failure.



\### Status Code Decision Table



| Scenario                                  | Status Code |

|--------------------------------------------|-------------|

| Resource fetched successfully               | 200         |

| Resource created successfully               | 201         |

| Resource deleted successfully               | 204         |

| Client sent invalid/malformed data          | 400         |

| Client not authenticated                    | 401         |

| Client authenticated but not allowed         | 403         |

| Resource does not exist                     | 404         |

| Request conflicts with current state         | 409         |

| Unexpected server-side failure              | 500         |



\### Anti-pattern to avoid

Never return 200 for every response, including errors, with the actual

result hidden inside the JSON body. Status codes must reflect what

actually happened.







\## Day 3 - SQL \& Relational Modeling



\### Schema

projects: id (PK), name, created\_at

tasks: id (PK), project\_id (FK -> projects.id), description, priority, done, created\_at



\### Constraints demonstrated

\- PRIMARY KEY (id on both tables) - guarantees uniqueness

\- NOT NULL (description, name) - required fields

\- FOREIGN KEY (tasks.project\_id -> projects.id) - a task must belong

&#x20; to a real, existing project; confirmed by an actual rejected insert

&#x20; when testing with a non-existent project\_id

\- DEFAULT values (priority, done, created\_at) - sensible starting values



\### Joins

SELECT tasks.description, tasks.priority, projects.name AS project\_name

FROM tasks JOIN projects ON tasks.project\_id = projects.id;

\- Combines both tables in one result, using the foreign key relationship



\### Transactions

A BEGIN...COMMIT block inserted a new project and its first task together.

If either insert had failed, ROLLBACK would have undone both, keeping

the database consistent.



\### Indexes

CREATE INDEX idx\_tasks\_project\_id ON tasks(project\_id);

Added because "list all tasks for a project" (the JOIN query above) is

a frequent lookup - the index lets Postgres find matching rows directly

instead of scanning the whole tasks table.


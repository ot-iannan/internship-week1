\## Day 3 - PostgreSQL Setup \& Migrations



\### Setup

\- PostgreSQL running via Docker (taskdb container)

\- Connected using GoLand's built-in database tool



\### Migration file: db/001\_create\_tables.sql

\- Creates projects and tasks tables

\- Uses IF NOT EXISTS so it can be safely re-run against a fresh database

\- Includes constraints: UNIQUE project name, CHECK title is not empty,

&#x20; ON DELETE CASCADE (deleting a project removes its tasks automatically)

\- Includes indexes on tasks.project\_id and tasks.completed



\### Seed file: db/002\_seed\_data.sql

\- Adds starter projects and tasks after migration runs



\### Environment configuration

\- Real credentials live in db/.env (DB\_HOST, DB\_PORT, DB\_NAME, DB\_USER, DB\_PASSWORD)

\- .env is listed in .gitignore and confirmed not tracked by Git

\- A safe .env.example template is committed instead, showing the

&#x20; required variable names without real values


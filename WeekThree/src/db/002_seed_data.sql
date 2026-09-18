INSERT INTO projects (name, description)
VALUES
    ('Learning Backend', 'My first real backend project with PostgreSQL'),
    ('Personal', 'Personal tasks and errands');

INSERT INTO tasks (project_id, title, description, completed)
VALUES
    (1, 'Set up PostgreSQL with Docker', 'Container is running and connected via DBeaver', true),
    (1, 'Create tables with primary & foreign keys', 'projects and tasks tables', true),
    (1, 'Practice basic SQL commands', 'INSERT, SELECT, UPDATE, DELETE, JOIN', false),
    (1, 'Learn about migrations', 'Make the database repeatable', false),
    (2, 'Buy groceries', NULL, false),
    (2, 'Read a book', 'At least 20 pages', false);
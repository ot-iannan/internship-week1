CREATE TABLE IF NOT EXISTS projects (
                          id          SERIAL PRIMARY KEY,
                          name        TEXT NOT NULL,
                          description TEXT,
                          created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tasks (
                       id          SERIAL PRIMARY KEY,
                       project_id  INTEGER REFERENCES projects(id) ON DELETE CASCADE,
                       title       TEXT NOT NULL,
                       description TEXT,
                       completed   BOOLEAN NOT NULL DEFAULT FALSE,
                       created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                       updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Constraints
ALTER TABLE projects
    ADD CONSTRAINT unique_project_name UNIQUE (name);

ALTER TABLE tasks
    ADD CONSTRAINT title_not_empty CHECK (title <> '');

-- Indexes
CREATE INDEX IF NOT EXISTS idx_tasks_project_id ON tasks(project_id);
CREATE INDEX IF NOT EXISTS idx_tasks_completed ON tasks(completed);
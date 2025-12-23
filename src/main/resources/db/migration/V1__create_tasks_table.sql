CREATE TABLE tasks (
                       id UUID PRIMARY KEY,

                       project_id UUID NOT NULL,

                       title VARCHAR(255) NOT NULL,
                       description TEXT,

                       status VARCHAR(50) NOT NULL,

                       assignee_id UUID,
                       due_date DATE,

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_tasks_project_id ON tasks(project_id);
CREATE INDEX idx_tasks_assignee_id ON tasks(assignee_id);
CREATE INDEX idx_tasks_status ON tasks(status);

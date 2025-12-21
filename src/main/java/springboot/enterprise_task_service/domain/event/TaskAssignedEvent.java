package springboot.enterprise_task_service.domain.event;

import java.time.Instant;
import java.util.UUID;

public final class TaskAssignedEvent implements DomainEvent {

    private final UUID taskId;
    private final UUID projectId;
    private final UUID assigneeId;
    private final Instant occurredAt;

    public TaskAssignedEvent(
            UUID taskId,
            UUID projectId,
            UUID assigneeId,
            Instant occurredAt
    ) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.assigneeId = assigneeId;
        this.occurredAt = occurredAt;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public UUID getAssigneeId() {
        return assigneeId;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }
}

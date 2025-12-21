package springboot.enterprise_task_service.domain.event;

import springboot.enterprise_task_service.domain.model.TaskStatus;
import java.time.Instant;
import java.util.UUID;

public final class TaskStatusChangedEvent implements DomainEvent {

    private final UUID taskId;
    private final UUID projectId;
    private final TaskStatus oldStatus;
    private final TaskStatus newStatus;
    private final Instant occurredAt;

    public TaskStatusChangedEvent(
            UUID taskId,
            UUID projectId,
            TaskStatus oldStatus,
            TaskStatus newStatus,
            Instant occurredAt
    ) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.occurredAt = occurredAt;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public TaskStatus getOldStatus() {
        return oldStatus;
    }

    public TaskStatus getNewStatus() {
        return newStatus;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }
}
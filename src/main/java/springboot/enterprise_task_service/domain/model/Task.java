package springboot.enterprise_task_service.domain.model;

import lombok.Builder;
import lombok.Getter;
import springboot.enterprise_task_service.domain.model.exception.InvalidTaskStateTransitionException;
import springboot.enterprise_task_service.domain.event.DomainEvent;
import springboot.enterprise_task_service.domain.event.TaskStatusChangedEvent;
import springboot.enterprise_task_service.domain.event.TaskAssignedEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Task {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    @Getter
    private final UUID id;
    @Getter
    private final UUID projectId;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private TaskStatus status;
    @Getter
    private UUID assigneeId;
    @Getter
    private final Instant createdAt;
    @Getter
    private LocalDate dueDate;

    private Task(
            UUID id,
            UUID projectId,
            String title,
            String description,
            TaskStatus status,
            UUID assigneeId,
            Instant createdAt,
            LocalDate dueDate
    ) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
    }

    // Factory method
    public static Task create(UUID projectId, String title, String description, LocalDate dueDate) {
        Objects.requireNonNull(projectId, "projectId must not be null");
        Objects.requireNonNull(title, "title must not be null");

        return new Task(
                UUID.randomUUID(),
                projectId,
                title,
                description,
                TaskStatus.PENDING,
                null,
                Instant.now(),
                dueDate
        );
    }

    // --- Behavior ---

    public void start() {
        transitionTo(TaskStatus.IN_PROGRESS);
    }

    public void complete() {
        transitionTo(TaskStatus.DONE);
    }

    public void archive() {
        transitionTo(TaskStatus.ARCHIVED);
    }

    public void assignTo(UUID assigneeId) {
        Objects.requireNonNull(assigneeId, "assigneeId must not be null");

        if (this.status == TaskStatus.ARCHIVED) {
            throw new IllegalStateException("Cannot assign an archived task");
        }

        this.assigneeId = assigneeId;

        domainEvents.add(
                new TaskAssignedEvent(
                        this.id,
                        this.projectId,
                        assigneeId,
                        Instant.now()
                )
        );
    }

    public boolean isOverdue() {
        return dueDate != null
                && Instant.now().isAfter(dueDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC))
                && status != TaskStatus.DONE
                && status != TaskStatus.ARCHIVED;
    }

    // --- Internal invariant enforcement ---

    private void transitionTo(TaskStatus targetStatus) {
        if (!this.status.canTransitionTo(targetStatus)) {
            throw new InvalidTaskStateTransitionException(
                    "Cannot transition task from " + status + " to " + targetStatus
            );
        }

        TaskStatus oldStatus = this.status;
        this.status = targetStatus;

        domainEvents.add(
                new TaskStatusChangedEvent(
                        this.id,
                        this.projectId,
                        oldStatus,
                        targetStatus,
                        Instant.now()
                )
        );
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }

    public static Task restore(
            UUID id,
            UUID projectId,
            String title,
            String description,
            TaskStatus status,
            UUID assigneeId,
            Instant createdAt,
            LocalDate dueDate
    ) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(projectId, "projectId must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");

        Task task = new Task(id, projectId, title, description, status, assigneeId, createdAt,  dueDate);

        // VERY IMPORTANT:
        // restored aggregates must start with NO domain events
        task.domainEvents.clear();

        return task;
    }
}


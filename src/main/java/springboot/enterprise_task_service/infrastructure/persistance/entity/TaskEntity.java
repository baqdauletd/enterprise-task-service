package springboot.enterprise_task_service.infrastructure.persistance.entity;

import lombok.Getter;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @Getter
    private UUID id;

    @Column(nullable = false)
    @Getter
    private UUID projectId;

    @Column(nullable = false)
    @Getter
    private String title;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column
    @Getter
    private UUID assigneeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    private TaskStatusEntity statusEntity;

    @Column(nullable = false,  updatable = false)
    @Getter
    private Instant createdAt;

    @Column(nullable = false)
    @Getter
    private LocalDate dueDate;

    protected TaskEntity() {
        // JPA only
    }

    public TaskEntity(
            UUID id,
            UUID projectId,
            String title,
            String description,
            TaskStatusEntity statusEntity,
            UUID assigneeId,
            Instant createdAt,
            LocalDate dueDate
    ) {
        this.id = id;
        this.projectId = projectId;
        this.assigneeId = assigneeId;
        this.statusEntity = statusEntity;
        this.createdAt = createdAt;
        this.description = description;
        this.dueDate = dueDate;
        this.title = title;
    }
}

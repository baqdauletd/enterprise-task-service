package springboot.enterprise_task_service.infrastructure.persistance.mapper;

import springboot.enterprise_task_service.domain.model.Task;
import springboot.enterprise_task_service.infrastructure.persistance.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getProjectId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssigneeId(),
                task.getCreatedAt(),
                task.getDueDate()
        );
    }

    public static Task toDomain(TaskEntity entity) {
        return Task.restore(
                entity.getId(),
                entity.getProjectId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getAssigneeId(),
                entity.getCreatedAt(),
                entity.getDueDate()
        );
    }
}

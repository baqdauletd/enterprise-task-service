package springboot.enterprise_task_service.infrastructure.persistance.mapper;

import springboot.enterprise_task_service.domain.model.Task;
import springboot.enterprise_task_service.infrastructure.persistance.entity.TaskEntity;
import springboot.enterprise_task_service.infrastructure.persistance.entity.TaskStatusEntity;
import springboot.enterprise_task_service.domain.model.TaskStatus;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {
    @Mapping(target="status", source="status")
    TaskEntity ToEntity(Task task);

    @Mapping(target="this", expression = "java(restoreTask(entity))")
    Task toDomain(TaskEntity taskEntity);

    default Task restoreTask(TaskEntity taskEntity) {
        return Task.restore(
                taskEntity.getId(),
                taskEntity.getProjectId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                mapToTaskStatus(taskEntity.getStatus()),
                taskEntity.getAssigneeId(),
                taskEntity.getCreatedAt(),
                taskEntity.getDueDate()
        );
    }

    default TaskStatus mapToTaskStatus(TaskStatus taskStatus) {
        return TaskStatus.valueOf(taskStatus.name());
    }

    default TaskStatusEntity mapToTaskStatusEntity(TaskStatusEntity taskStatusEntity) {
        return TaskStatusEntity.valueOf(taskStatusEntity.name());
    }
}

package springboot.enterprise_task_service.application.service;

import springboot.enterprise_task_service.domain.model.Task;

import java.time.LocalDate;
import java.util.UUID;

public interface TaskService {

    Task createTask(UUID projectId, String title, String description, LocalDate dueDate);

    Task assignTask(UUID taskId, UUID assigneeId);

    Task startTask(UUID taskId);

    Task completeTask(UUID taskId);

    Task archiveTask(UUID taskId);

    Task getTask(UUID taskId);
}
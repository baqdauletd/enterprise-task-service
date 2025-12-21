package springboot.enterprise_task_service.application.service;

import java.time.LocalDate;
import java.util.UUID;

public interface TaskService {

    UUID createTask(UUID projectId,  String title, String description, LocalDate dueDate);

    void assignTask(UUID taskId, UUID assigneeId);

    void startTask(UUID taskId);

    void completeTask(UUID taskId);

    void archiveTask(UUID taskId);
}
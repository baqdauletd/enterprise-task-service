package springboot.enterprise_task_service.application.service;

import springboot.enterprise_task_service.application.event.DomainEventPublisher;
import springboot.enterprise_task_service.domain.model.Task;
import springboot.enterprise_task_service.domain.repository.TaskRepository;

import java.time.LocalDate;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final DomainEventPublisher eventPublisher;

    public TaskServiceImpl(TaskRepository taskRepository, DomainEventPublisher eventPublisher) {
        this.taskRepository = taskRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public UUID createTask(UUID projectId, String title, String description, LocalDate dueTime) {
        Task task = Task.create(projectId,  title, description, dueTime);

        taskRepository.save(task);
        eventPublisher.publish(task.pullDomainEvents());

        return task.getId();
    }

    @Override
    public void assignTask(UUID taskId, UUID assigneeId) {
        Task task = loadTask(taskId);

        task.assignTo(assigneeId);

        taskRepository.save(task);
        eventPublisher.publish(task.pullDomainEvents());
    }

    @Override
    public void startTask(UUID taskId) {
        Task task = loadTask(taskId);

        task.start();

        taskRepository.save(task);
        eventPublisher.publish(task.pullDomainEvents());
    }

    @Override
    public void completeTask(UUID taskId) {
        Task task = loadTask(taskId);

        task.complete();

        taskRepository.save(task);
        eventPublisher.publish(task.pullDomainEvents());
    }

    @Override
    public void archiveTask(UUID taskId) {
        Task task = loadTask(taskId);

        task.archive();

        taskRepository.save(task);
        eventPublisher.publish(task.pullDomainEvents());
    }

    private Task loadTask(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task not found: " + taskId
                ));
    }
}

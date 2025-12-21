package springboot.enterprise_task_service.domain.repository;

import springboot.enterprise_task_service.domain.model.Task;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    Optional<Task> findById(UUID taskId);

    void save(Task task);

    boolean existsById(UUID taskId);
}

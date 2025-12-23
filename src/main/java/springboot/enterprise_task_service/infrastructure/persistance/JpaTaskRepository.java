package springboot.enterprise_task_service.infrastructure.persistance;

import springboot.enterprise_task_service.domain.model.Task;
import springboot.enterprise_task_service.domain.repository.TaskRepository;
import springboot.enterprise_task_service.infrastructure.persistance.entity.TaskEntity;
import springboot.enterprise_task_service.infrastructure.persistance.mapper.TaskEntityMapper;
import springboot.enterprise_task_service.infrastructure.persistance.spring.SpringDataTaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTaskRepository implements TaskRepository {

    private final SpringDataTaskRepository springRepository;

    public JpaTaskRepository(SpringDataTaskRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<Task> findById(UUID taskId) {
        return springRepository.findById(taskId)
                .map(TaskEntityMapper::toDomain);
    }

    @Override
    public void save(Task task) {
        TaskEntity entity = TaskEntityMapper.toEntity(task);
        springRepository.save(entity);
    }

    @Override
    public boolean existsById(UUID taskId) {
        return springRepository.existsById(taskId);
    }
}

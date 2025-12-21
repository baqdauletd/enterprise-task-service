package springboot.enterprise_task_service.infrastructure.persistance.spring;

import springboot.enterprise_task_service.infrastructure.persistance.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, UUID> {}

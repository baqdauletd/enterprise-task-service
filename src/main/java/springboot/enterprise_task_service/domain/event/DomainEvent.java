package springboot.enterprise_task_service.domain.event;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredAt();
}

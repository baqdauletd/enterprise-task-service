package springboot.enterprise_task_service.application.event;

import springboot.enterprise_task_service.domain.event.DomainEvent;

import java.util.List;

public interface DomainEventPublisher {

    void publish(List<DomainEvent> events);
}

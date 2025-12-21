package springboot.enterprise_task_service.infrastructure;

import springboot.enterprise_task_service.application.event.DomainEventPublisher;
import springboot.enterprise_task_service.domain.event.DomainEvent;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LoggingDomainEventPublisher implements DomainEventPublisher {

    @Override
    public void publish(List<DomainEvent> events) {
        // temporary no-op or log
        events.forEach(event ->
                System.out.println("Domain event published: " + event.getClass().getSimpleName())
        );
    }
}

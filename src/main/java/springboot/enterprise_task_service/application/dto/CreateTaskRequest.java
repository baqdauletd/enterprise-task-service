package springboot.enterprise_task_service.application.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.enterprise_task_service.domain.model.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public class CreateTaskRequest {

    @Getter
    @Setter
    private UUID projectId;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private TaskStatus status;

    @Getter
    @Setter
    private UUID assigneeID;

    @Getter
    @Setter
    private LocalDate deuDate;
}

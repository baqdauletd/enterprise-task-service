package springboot.enterprise_task_service.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class AssignTaskRequest {

    @Getter
    @Setter
    private UUID assigneeId;
}


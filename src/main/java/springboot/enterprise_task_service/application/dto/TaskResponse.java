package springboot.enterprise_task_service.application.dto;

import lombok.Getter;

import java.util.UUID;

public class TaskResponse {

    @Getter
    private UUID id;

    public TaskResponse(UUID id) {
        this.id = id;
    }
}

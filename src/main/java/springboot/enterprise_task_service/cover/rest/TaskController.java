package springboot.enterprise_task_service.cover.rest;

import org.springframework.stereotype.Controller;
import springboot.enterprise_task_service.application.dto.AssignTaskRequest;
import springboot.enterprise_task_service.application.dto.CreateTaskRequest;
import springboot.enterprise_task_service.application.dto.TaskResponse;
import springboot.enterprise_task_service.application.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        UUID taskId = taskService.createTask(request.getProjectId(), request.getTitle(), request.getDescription(), request.getDeuDate());
        return ResponseEntity
                .created(URI.create("/tasks/" + taskId))
                .body(new TaskResponse(taskId));
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<Void> assignTask(
            @PathVariable UUID taskId,
            @RequestBody AssignTaskRequest request
    ) {
        taskService.assignTask(taskId, request.getAssigneeId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/start")
    public ResponseEntity<Void> startTask(@PathVariable UUID taskId) {
        taskService.startTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID taskId) {
        taskService.completeTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/archive")
    public ResponseEntity<Void> archiveTask(@PathVariable UUID taskId) {
        taskService.archiveTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

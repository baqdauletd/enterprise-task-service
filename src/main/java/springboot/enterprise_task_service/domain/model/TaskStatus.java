package springboot.enterprise_task_service.domain.model;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    DONE,
    ARCHIVED;

    public boolean canTransitionTo(TaskStatus target) {
        return switch (this) {
            case PENDING -> target == IN_PROGRESS;
            case IN_PROGRESS -> target == DONE;
            case DONE -> target == ARCHIVED;
            case ARCHIVED -> false;
        };
    }
}
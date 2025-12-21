package springboot.enterprise_task_service.domain.model.exception;

public class InvalidTaskStateTransitionException extends RuntimeException {

    public InvalidTaskStateTransitionException(String message) {
        super(message);
    }
}
package leo_test.trs.sa.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import leo_test.trs.sa.dto.ErrorEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApplicationControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorEntity handleException(EntityNotFoundException e) {
        return new ErrorEntity(null, e.getMessage());
    }
}

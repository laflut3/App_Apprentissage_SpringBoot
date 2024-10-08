package leo_test.trs.sa.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import leo_test.trs.sa.dto.ErrorEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ErrorEntity handleException(EntityNotFoundException e) {
        return new ErrorEntity(null, e.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody ErrorEntity handleRuntimeException(RuntimeException e) {
        return new ErrorEntity(null, e.getMessage());
    }
}

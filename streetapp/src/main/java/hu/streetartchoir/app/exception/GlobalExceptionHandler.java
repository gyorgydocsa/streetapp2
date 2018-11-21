package hu.streetartchoir.app.exception;

import hu.streetartchoir.app.dto.ValidationErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<ValidationErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    protected ResponseEntity<ValidationErrorDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.warn(ex.getMessage(), ex);
        ValidationErrorDto dto = new ValidationErrorDto();
        dto.setGlobalError(ex.getMessage());
        dto.addFieldError("id", ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    protected ResponseEntity<ValidationErrorDto> throwable(Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        ValidationErrorDto dto = new ValidationErrorDto();
        dto.setGlobalError(ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDto dto = new ValidationErrorDto();

        for (FieldError fieldError : fieldErrors) {
            dto.addFieldError(fieldError.getField(), messageSource.getMessage(fieldError, null));
        }

        return dto;
    }
}

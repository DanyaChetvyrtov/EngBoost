package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToFetchData;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseData;
import app.first.myEng.engBoost.models.exception.ExceptionBody;
import app.first.myEng.engBoost.models.exception.ResourceNotFound;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFound e) {
        logger.error("Ресурс не найден. ", e);
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMismatchedInputException(MismatchedInputException e) {
        logger.error("Ошибка: ", e);
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var exceptionBody = new ExceptionBody("Validation failed");

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(fieldErrors.stream().collect(
                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
        ));
        logger.error("Ошибка валидации: ", e);
        exceptionBody.getErrors().forEach(
                (key, value) -> logger.warn("Ошибка валидации поля '{}' : '{}'", key, value));

        return exceptionBody;
    }

    @ExceptionHandler(FailToParseData.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleFailToParseWord(FailToParseData e) {
        e.printStackTrace();
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(FailToFetchData.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleFailToFetchWord(FailToFetchData e) {
        e.printStackTrace();
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleException(Exception e) {
        return new ExceptionBody("Internal server error");
    }
}

package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToFetchData;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseData;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NoSuchElementExceptionHandler() {
        return "Not found";
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MismatchedInputExceptionHandler(MismatchedInputException e) {
        e.printStackTrace();
        return "Bad request";
    }

    @ExceptionHandler(FailToParseData.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String FailToParseWordHandler(FailToParseData e) {
        e.printStackTrace();
        return "Fail to parse word";
    }

    @ExceptionHandler(FailToFetchData.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String FailToFetchWordHandler(FailToFetchData e) {
        e.printStackTrace();
        return "Failed to fetch word data from API";
    }
}

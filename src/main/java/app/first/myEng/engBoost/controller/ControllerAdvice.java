package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToFetchWord;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseWord;
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
    public String MismatchedInputExceptionHandler() {
        return "Bad request";
    }

    @ExceptionHandler(FailToParseWord.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String FailToParseWordHandler(FailToParseWord e) {
        e.printStackTrace();
        return "Fail to parse word";
    }

    @ExceptionHandler(FailToFetchWord.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String FailToFetchWordHandler(FailToFetchWord e) {
        e.printStackTrace();
        return "Failed to fetch word data from API";
    }
}

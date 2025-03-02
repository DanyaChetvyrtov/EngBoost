package app.first.myEng.engBoost.api.merriamwebster.model.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FailToParseWord extends JsonProcessingException {
    public FailToParseWord(String message) {
        super(message);
    }
}

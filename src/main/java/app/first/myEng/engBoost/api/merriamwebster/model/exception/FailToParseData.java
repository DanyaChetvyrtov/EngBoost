package app.first.myEng.engBoost.api.merriamwebster.model.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FailToParseData extends JsonProcessingException {
    public FailToParseData(String message) {
        super(message);
    }
}

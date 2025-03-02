package app.first.myEng.engBoost.api.merriamwebster.model.exception;

import org.springframework.web.client.RestClientException;

public class FailToFetchWord extends RestClientException {
    public FailToFetchWord(String message) {
        super(message);
    }
}

package app.first.myEng.engBoost.api.merriamwebster.model.exception;

import org.springframework.web.client.RestClientException;

public class FailToFetchData extends RestClientException {
    public FailToFetchData(String message) {
        super(message);
    }
}

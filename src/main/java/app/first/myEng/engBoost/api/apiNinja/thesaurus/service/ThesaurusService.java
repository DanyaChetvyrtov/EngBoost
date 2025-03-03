package app.first.myEng.engBoost.api.apiNinja.thesaurus.service;

import app.first.myEng.engBoost.api.apiNinja.thesaurus.constants.ThesaurusApiProps;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThesaurusService {
    private final RestTemplate restTemplate;
    private final ThesaurusApiProps apiProps;

    public ThesaurusService(RestTemplate restTemplate, ThesaurusApiProps apiProps) {
        this.restTemplate = restTemplate;
        this.apiProps = apiProps;
    }

    public String fetchWordData(String word) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiProps.getApiKey());

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiProps.getUrl() + "?word=" + word, HttpMethod.GET, requestEntity, String.class);

        return response.getBody(); // json as string
    }
}

package app.first.myEng.engBoost.api.merriamwebster.service;

import app.first.myEng.engBoost.api.merriamwebster.constants.MerriamWebsterApiProps;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class MerriamWebsterService {
    private final RestTemplate restTemplate;
    private final MerriamWebsterApiProps apiProps;

    public MerriamWebsterService(RestTemplate restTemplate, MerriamWebsterApiProps apiProps) {
        this.restTemplate = restTemplate;
        this.apiProps = apiProps;
    }

    public String fetchWordData(String word) {
        return restTemplate.getForObject(
                MessageFormat.format("{0}/{1}?key={2}",
                        apiProps.getUrl(), word, apiProps.getApiKey()), String.class
        );
    }
}

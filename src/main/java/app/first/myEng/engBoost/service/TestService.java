package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToFetchWord;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseWord;
import app.first.myEng.engBoost.api.merriamwebster.service.MerriamWebsterService;
import app.first.myEng.engBoost.api.merriamwebster.utils.WordParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class TestService {
    private final WordParser wordParser;
    private final MerriamWebsterService merriamWebsterService;

    public TestService(WordParser wordParser, MerriamWebsterService merriamWebsterService) {
        this.wordParser = wordParser;
        this.merriamWebsterService = merriamWebsterService;
    }

    public WordInfo getWordInfo(String word) throws FailToParseWord {
        try {
            String json = merriamWebsterService.fetchWordData(word);
            return wordParser.parseWord(json, word);
        } catch (JsonProcessingException e) {
            throw new FailToParseWord(e.getMessage());
        } catch (RestClientException e) {
            throw new FailToFetchWord(e.getMessage());
        }
    }
}

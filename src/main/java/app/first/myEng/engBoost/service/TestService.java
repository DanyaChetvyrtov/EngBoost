package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.service.MerriamWebsterService;
import app.first.myEng.engBoost.api.merriamwebster.utils.WordParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final WordParser wordParser;
    private final MerriamWebsterService merriamWebsterService;

    public TestService(WordParser wordParser, MerriamWebsterService merriamWebsterService) {
        this.wordParser = wordParser;
        this.merriamWebsterService = merriamWebsterService;
    }

    public WordInfo getWordInfo(String word) throws JsonProcessingException {
        return wordParser.parseWord(merriamWebsterService.fetchWordData(word), word);
    }
}

package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.api.apiNinja.thesaurus.model.WordSynonymsAntonyms;
import app.first.myEng.engBoost.api.apiNinja.thesaurus.service.ThesaurusService;
import app.first.myEng.engBoost.api.apiNinja.thesaurus.utils.SynonymsAntonymsParser;
import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToFetchData;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseData;
import app.first.myEng.engBoost.api.merriamwebster.service.MerriamWebsterService;
import app.first.myEng.engBoost.api.merriamwebster.utils.WordParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class OuterApiService {
    private final WordParser wordParser;
    private final SynonymsAntonymsParser synonymsAntonymsParser;
    private final MerriamWebsterService merriamWebsterService;
    private final ThesaurusService thesaurusService;

    public OuterApiService(WordParser wordParser, SynonymsAntonymsParser synonymsAntonymsParser, MerriamWebsterService merriamWebsterService, ThesaurusService thesaurusService) {
        this.wordParser = wordParser;
        this.synonymsAntonymsParser = synonymsAntonymsParser;
        this.merriamWebsterService = merriamWebsterService;
        this.thesaurusService = thesaurusService;
    }

    public WordInfo getWordInfo(String word) throws FailToParseData {
        try {
            String json = merriamWebsterService.fetchWordData(word);
            return wordParser.parse(json, word);
        } catch (JsonProcessingException e) {
            throw new FailToParseData(e.getMessage());
        } catch (RestClientException e) {
            throw new FailToFetchData(e.getMessage());
        }
    }

    public WordSynonymsAntonyms getWordSynonymsAntonyms(String word) throws FailToParseData {
        try {
            String json = thesaurusService.fetchWordData(word);
            return synonymsAntonymsParser.parse(json);
        } catch (JsonProcessingException e) {
            throw new FailToParseData(e.getMessage());
        } catch (RestClientException e) {
            throw new FailToFetchData(e.getMessage());
        }
    }
}

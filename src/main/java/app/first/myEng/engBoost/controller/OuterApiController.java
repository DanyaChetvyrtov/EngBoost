package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.api.apiNinja.thesaurus.model.WordSynonymsAntonyms;
import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseData;
import app.first.myEng.engBoost.service.OuterApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/outer/words")
public class OuterApiController {
    private final OuterApiService outerApiService;
    private final static Logger logger = LoggerFactory.getLogger(OuterApiController.class);

    public OuterApiController(OuterApiService outerApiService) {
        this.outerApiService = outerApiService;
    }

    @GetMapping("/{word}")
    public ResponseEntity<WordInfo> getWordInfo(@PathVariable("word") String word) throws FailToParseData {
        logger.info("GET request for '{}' word has been received", word);
        return new ResponseEntity<>(outerApiService.getWordInfo(word), HttpStatus.OK);
    }

    @GetMapping("/{word}/synonyms")
    public ResponseEntity<WordSynonymsAntonyms> getWordSynonyms(@PathVariable("word") String word) throws FailToParseData {
        logger.info("GET request for synonyms/antonyms for '{}' word has been received", word);
        return new ResponseEntity<>(outerApiService.getWordSynonymsAntonyms(word), HttpStatus.OK);
    }
}

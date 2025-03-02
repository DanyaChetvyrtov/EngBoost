package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseWord;
import app.first.myEng.engBoost.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test/{word}")
    public ResponseEntity<WordInfo> getWordInfo(@PathVariable("word") String word) throws FailToParseWord {
        return new ResponseEntity<>(testService.getWordInfo(word), HttpStatus.OK);
    }

}

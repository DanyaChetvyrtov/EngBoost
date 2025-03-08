package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/words")
public class WordCardController {
    private final WordCardService wordCardService;

    public WordCardController(WordCardService wordCardService) {
        this.wordCardService = wordCardService;
    }

    @GetMapping("/{word}")
    public ResponseEntity<WordCard> getWordCard(@PathVariable("word") String word) {
        return new ResponseEntity<>(wordCardService.getWordCard(word), HttpStatus.OK);
    }

}

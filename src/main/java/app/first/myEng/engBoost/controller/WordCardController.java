package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<WordCard> addWordCard(@RequestBody WordCard wordCard) {
        return new ResponseEntity<>(wordCardService.create(wordCard), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WordCard> updateWordCard(@RequestBody WordCard wordCard, @PathVariable("id") int id) {
        return new ResponseEntity<>(wordCardService.update(wordCard, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWordCard(@PathVariable("id") int id) {
        wordCardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

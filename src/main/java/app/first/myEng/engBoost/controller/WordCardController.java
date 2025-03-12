package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.wordCard.WordCardDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import app.first.myEng.engBoost.utils.mapper.WordCardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/words")
public class WordCardController {
    private final WordCardService wordCardService;
    private final WordCardMapper wordCardMapper;

    public WordCardController(WordCardService wordCardService, WordCardMapper wordCardMapper) {
        this.wordCardService = wordCardService;
        this.wordCardMapper = wordCardMapper;
    }

    @GetMapping("/{word}")
    public ResponseEntity<WordCardDto> getWordCard(@PathVariable("word") String word) {
        WordCard wordCard = wordCardService.getWordCard(word);
        return new ResponseEntity<>(wordCardMapper.toDto(wordCard), HttpStatus.OK);
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

package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.wordCard.WordCardClientDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardExtendedDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import app.first.myEng.engBoost.utils.mapper.WordCardMapper;
import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<WordCardExtendedDto> getWordCard(@PathVariable("word") String word) {
        WordCard wordCard = wordCardService.getWordCard(word);
        return new ResponseEntity<>(wordCardMapper.toDto(wordCard), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WordCardExtendedDto> createWordCard(
            @RequestBody @Validated(OnCreate.class) WordCardClientDto wordCardClientDto) {
        WordCard wordCard = wordCardMapper.toEntity(wordCardClientDto);
        wordCard = wordCardService.create(wordCard);
        return new ResponseEntity<>(wordCardMapper.toDto(wordCard), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WordCardExtendedDto> updateWordCard(
            @RequestBody @Validated(OnUpdate.class) WordCardClientDto wordCardClientDto) {
        WordCard wordCard = wordCardMapper.toEntity(wordCardClientDto);
        wordCard = wordCardService.update(wordCard);
        return new ResponseEntity<>(wordCardMapper.toDto(wordCard), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWordCard(@PathVariable("id") int id) {
        wordCardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}/word-cards")
    public ResponseEntity<List<WordCardExtendedDto>> getWordCardByUsername(
            @PathVariable("username") String username) {
        List<WordCardExtendedDto> wordCardExtendedDtos = wordCardMapper
                .toDtoList(wordCardService.getUserCards(username));
        return new ResponseEntity<>(wordCardExtendedDtos, HttpStatus.OK);
    }
}

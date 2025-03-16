package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.wordCard.WordCardClientDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardExtendedDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import app.first.myEng.engBoost.utils.mapper.WordCardMapper;
import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(WordCardController.class);

    public WordCardController(WordCardService wordCardService, WordCardMapper wordCardMapper) {
        this.wordCardService = wordCardService;
        this.wordCardMapper = wordCardMapper;
    }

    @GetMapping("/{word}")
    public ResponseEntity<WordCardExtendedDto> getWordCard(@PathVariable("word") String word) {
        logger.info("GET request for card with '{}' word has been received.", word);
        WordCard wordCard = wordCardService.getWordCard(word);
        return new ResponseEntity<>(wordCardMapper.toExtendedDto(wordCard), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WordCardExtendedDto> createWordCard(
            @RequestBody @Validated(OnCreate.class) WordCardClientDto wordCardClientDto) {
        logger.info("POST request for card with '{}' word has been received.", wordCardClientDto.getWord());
        WordCard wordCard = wordCardMapper.toEntity(wordCardClientDto);
        wordCard = wordCardService.create(wordCard);
        return new ResponseEntity<>(wordCardMapper.toExtendedDto(wordCard), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WordCardExtendedDto> updateWordCard(
            @RequestBody @Validated(OnUpdate.class) WordCardClientDto wordCardClientDto) {
        logger.info("PUT request for card with id '{}' has been received.", wordCardClientDto.getId());
        WordCard wordCard = wordCardMapper.toEntity(wordCardClientDto);
        wordCard = wordCardService.update(wordCard);
        return new ResponseEntity<>(wordCardMapper.toExtendedDto(wordCard), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWordCard(@PathVariable("id") int id) {
        logger.info("DELETE request for card with id '{}' has been received.", id);
        wordCardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}/word-cards")
    public ResponseEntity<List<WordCardDto>> getWordCardByUsername(
            @PathVariable("username") String username) {
        logger.info("GET request for user '{}' cards has been received.", username);
        List<WordCardDto> wordCardExtendedDtos = wordCardMapper
                .toDtoList(wordCardService.getUserCards(username));
        return new ResponseEntity<>(wordCardExtendedDtos, HttpStatus.OK);
    }
}

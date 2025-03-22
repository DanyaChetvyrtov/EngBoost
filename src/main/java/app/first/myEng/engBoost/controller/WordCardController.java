package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.common.PageResponse;
import app.first.myEng.engBoost.dto.wordCard.WordCardDetailsDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardListItemDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardWriteDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.service.WordCardService;
import app.first.myEng.engBoost.utils.mapper.WordCardMapper;
import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<WordCardDetailsDto> getWordCard(@PathVariable("word") String word) {
        logger.info("GET request for card with '{}' word has been received.", word);
        WordCard wordCard = wordCardService.getWordCard(word);
        return new ResponseEntity<>(wordCardMapper.toExtendedDto(wordCard), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@customSecurityExpression.canAccessCard(#wordCardWriteDto.userId)")
    public ResponseEntity<WordCardDetailsDto> createWordCard(
            @RequestBody @Validated(OnCreate.class) WordCardWriteDto wordCardWriteDto) {
        logger.info("POST request for card with '{}' word has been received.", wordCardWriteDto.getWord());
        WordCard wordCard = wordCardMapper.toEntity(wordCardWriteDto);
        wordCard = wordCardService.create(wordCard);
        return new ResponseEntity<>(wordCardMapper.toExtendedDto(wordCard), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("@customSecurityExpression.canAccessCard(#wordCardWriteDto.userId)")
    public ResponseEntity<WordCardDetailsDto> updateWordCard(
            @RequestBody @Validated(OnUpdate.class) WordCardWriteDto wordCardWriteDto) {
        logger.info("PUT request for card with id '{}' has been received.", wordCardWriteDto.getId());
        WordCard wordCard = wordCardMapper.toEntity(wordCardWriteDto);
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
    public ResponseEntity<PageResponse<WordCardListItemDto>> getWordCardsByUsername(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            @PathVariable("username") String username) {
        logger.info("GET request for user '{}' cards has been received.", username);

        Page<WordCard> wordCardPage = wordCardService.getUserCards(page, size, username);
        List<WordCardListItemDto> wordCardDtos = wordCardMapper.toShortDtoList(wordCardPage.getContent());

        PageResponse<WordCardListItemDto> response = new PageResponse<>(
                wordCardDtos,
                wordCardPage.getTotalPages(),
                wordCardPage.getTotalElements(),
                page, size
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.exception.ResourceNotFound;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.repository.WordCardRepository;
import app.first.myEng.engBoost.repository.WordTypeEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WordCardService {
    private final WordCardRepository wordCardRepository;
    private final WordTypeEntityRepository wordTypeEntityRepository;
    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(WordCardService.class);

    public WordCardService(WordCardRepository wordCardRepository, WordTypeEntityRepository wordTypeEntityRepository, UserService userService) {
        this.wordCardRepository = wordCardRepository;
        this.wordTypeEntityRepository = wordTypeEntityRepository;
        this.userService = userService;
    }

    public WordCard getWordCard(String word) {
        return wordCardRepository.findByWord(word).orElseThrow(() -> new ResourceNotFound("Word not found"));
    }

    @Transactional
    public WordCard create(WordCard wordCard) {
        WordTypeEntity wordTypeEntity = wordCard.getWordType();

        // Проверяем есть ли в базе такой тип
        logger.info("Checking if wordType exists: {}", wordTypeEntity.getWordType());
        wordTypeEntity = wordTypeEntityRepository.findByWordType(wordTypeEntity.getWordType())
                .orElseThrow(() -> new ResourceNotFound("Word type not found"));

        User owner = userService.getUserById(wordCard.getUserId());
        logger.info("Creating wordCard. For user with username '{}'", owner.getUsername());

        wordCard.setCardOwner(owner);
        wordCard.setWordType(wordTypeEntity);
        return wordCardRepository.save(wordCard);
    }

    @Transactional
    public WordCard update(WordCard wordCard) {

        logger.info("Checking the existence of wordCard with id '{}'", wordCard.getId());
        WordCard wordCardToBeUpdated = wordCardRepository.findById(wordCard.getId())
                .orElseThrow(() -> new ResourceNotFound("Word not found"));

        User owner = userService.getUserById(wordCard.getUserId());

        logger.info("Updating wordCard. For user with username '{}'", owner.getUsername());
        wordCardToBeUpdated.setWord(wordCard.getWord());
        wordCardToBeUpdated.setCardOwner(owner);

        WordTypeEntity wordTypeEntity = wordCard.getWordType();
        wordTypeEntity = wordTypeEntityRepository.findByWordType(wordTypeEntity.getWordType())
                .orElseThrow(() -> new ResourceNotFound("Word type not found"));

        wordCard.setWordType(wordTypeEntity);
        wordCardToBeUpdated.setWordType(wordCard.getWordType());
        wordCardToBeUpdated.setDefinition(wordCard.getDefinition());

        wordCardToBeUpdated.getStems().clear();
        wordCardToBeUpdated.getStems().addAll(wordCard.getStems());

        wordCardToBeUpdated.getExamples().clear();
        wordCardToBeUpdated.getExamples().addAll(wordCard.getExamples());

        wordCardToBeUpdated.getShortDefinitions().clear();
        wordCardToBeUpdated.getShortDefinitions().addAll(wordCard.getShortDefinitions());

        return wordCardRepository.save(wordCardToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting wordCard. id: {}", id);
        wordCardRepository.deleteById(id);
    }

    public List<WordCard> getUserCards(String username) {
        logger.info("Receiving '{}' cards. ", username);
        return wordCardRepository.findCardsByCardOwnerUsername(username).orElse(new ArrayList<>());
    }
}

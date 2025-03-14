package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.repository.WordCardRepository;
import app.first.myEng.engBoost.repository.WordTypeEntityRepository;
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


    public WordCardService(WordCardRepository wordCardRepository, WordTypeEntityRepository wordTypeEntityRepository, UserService userService) {
        this.wordCardRepository = wordCardRepository;
        this.wordTypeEntityRepository = wordTypeEntityRepository;
        this.userService = userService;
    }

    public WordCard getWordCard(String word) {
        return wordCardRepository.findByWord(word).orElseThrow(() -> new RuntimeException("Word not found"));
    }

    @Transactional
    public WordCard create(WordCard wordCard) {
        WordTypeEntity wordTypeEntity = wordCard.getWordType();

        // Проверяем есть ли в базе такой тип
        wordTypeEntity = wordTypeEntityRepository.findByWordType(wordTypeEntity.getWordType())
                .orElseThrow(() -> new RuntimeException("Word type not found"));

        User owner = userService.getUserById(wordCard.getUserId());

        System.out.println(wordCard.getId());
        wordCard.setCardOwner(owner);
        wordCard.setWordType(wordTypeEntity);
        return wordCardRepository.save(wordCard);
    }

    @Transactional
    public WordCard update(WordCard wordCard) {
        if(wordCard == null) throw new IllegalArgumentException("Word card must be not null");
        WordCard wordCardToBeUpdated = wordCardRepository.findById(wordCard.getId())
                .orElseThrow(() -> new RuntimeException("Word not found"));

        User owner = userService.getUserById(wordCard.getUserId());

        System.out.println(wordCard);
        System.out.println(owner);

        wordCardToBeUpdated.setWord(wordCard.getWord());
        wordCardToBeUpdated.setCardOwner(owner);

        WordTypeEntity wordTypeEntity = wordCard.getWordType();
        wordTypeEntity = wordTypeEntityRepository.findByWordType(wordTypeEntity.getWordType())
                .orElseThrow(() -> new RuntimeException("Word type not found"));

//        System.out.println(wordTypeEntity);
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
        wordCardRepository.deleteById(id);
    }

    public List<WordCard> getUserCards(String username){
        return wordCardRepository.findCardsByCardOwnerUsername(username).orElse(new ArrayList<>());
    }
}

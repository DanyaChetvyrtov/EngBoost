package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.wordCard.WordCard;
import app.first.myEng.engBoost.repository.WordCardRepository;
import org.springframework.stereotype.Service;

@Service
public class WordCardService {
    private final WordCardRepository wordCardRepository;

    public WordCardService(WordCardRepository wordCardRepository) {
        this.wordCardRepository = wordCardRepository;
    }

    public WordCard getWordCard(String word) {
        return wordCardRepository.findByWord(word).orElseThrow(() -> new RuntimeException("Word not found"));
    }
}

package app.first.myEng.engBoost.repository;

import app.first.myEng.engBoost.models.wordCard.WordCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordCardRepository extends JpaRepository<WordCard, Integer> {
    Optional<WordCard> findByWord(String word);

    Page<WordCard> findByCardOwnerUsername(String username, Pageable pageable);

    Optional<List<WordCard>> searchByWordContaining(String word);
}

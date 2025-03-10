package app.first.myEng.engBoost.repository;

import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.models.wordCard.enumirate.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordTypeEntityRepository extends JpaRepository<WordTypeEntity, Integer> {
    Optional<WordTypeEntity> findByWordType(WordType wordType);
}

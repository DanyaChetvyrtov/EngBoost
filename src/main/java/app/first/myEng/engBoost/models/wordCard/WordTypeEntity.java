package app.first.myEng.engBoost.models.wordCard;

import app.first.myEng.engBoost.models.wordCard.enumirate.WordType;
import jakarta.persistence.*;

@Entity
@Table(name = "word_type")
public class WordTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WordType wordType;

    public WordTypeEntity() {
    }

    public WordTypeEntity(Integer id, WordType wordType) {
        this.id = id;
        this.wordType = wordType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WordType getWordType() {
        return wordType;
    }

    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return "WordTypeEntity{" +
                "id=" + id +
                ", wordType=" + wordType +
                '}';
    }
}

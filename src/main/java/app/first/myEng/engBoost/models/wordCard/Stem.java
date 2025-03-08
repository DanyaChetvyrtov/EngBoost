package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;

@Entity
@Table(name = "stems")
public class Stem {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "word")
    private String word;

    public Stem() {
    }

    public Stem(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Stem{" +
                "id=" + id +
                ", word=" + word +
                '}';
    }
}

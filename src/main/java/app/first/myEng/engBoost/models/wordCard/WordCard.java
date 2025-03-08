package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "word_card")
public class WordCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "word")
    private String word;
    @Column(name = "definition")
    private String definition;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "word_card_id")
    private List<Stem> stems;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_card_id")
    private List<Example> examples;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_card_id")
    private List<ShortDefinition> shortDefinitions;

    public WordCard() {
    }

    public WordCard(Integer id, String word, String definition, LocalDateTime createdAt, LocalDateTime updatedAt, List<Stem> stems, List<Example> examples, List<ShortDefinition> shortDefinitions) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stems = stems;
        this.examples = examples;
        this.shortDefinitions = shortDefinitions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<Stem> getStems() {
        return stems;
    }

    public void setStems(List<Stem> stems) {
        this.stems = stems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<ShortDefinition> getShortDefinitions() {
        return shortDefinitions;
    }

    public void setShortDefinitions(List<ShortDefinition> shortDefinitions) {
        this.shortDefinitions = shortDefinitions;
    }

    @Override
    public String toString() {
        return "WordCard{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}

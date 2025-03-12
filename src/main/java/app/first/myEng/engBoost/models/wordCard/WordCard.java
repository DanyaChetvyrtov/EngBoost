package app.first.myEng.engBoost.models.wordCard;

import app.first.myEng.engBoost.utils.WordTypeEntityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "word_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "word")
    private String word;
    @Column(name = "definition")
    private String definition;

    @ManyToOne
    @JoinColumn(name = "word_type")
    @JsonDeserialize(using = WordTypeEntityDeserializer.class)
    private WordTypeEntity wordType;

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

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

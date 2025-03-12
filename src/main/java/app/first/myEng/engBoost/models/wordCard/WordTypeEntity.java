package app.first.myEng.engBoost.models.wordCard;

import app.first.myEng.engBoost.models.wordCard.enumirate.WordType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "word_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WordType wordType;
}

package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "word")
    private String word;
}

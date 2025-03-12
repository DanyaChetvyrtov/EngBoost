package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "short_definitions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "definition")
    private String definition;
}

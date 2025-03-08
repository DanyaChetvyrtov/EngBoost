package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;

@Entity
@Table(name = "short_definitions")
public class ShortDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "definition")
    private String definition;

    public ShortDefinition() {
    }

    public ShortDefinition(Integer id, String definition) {
        this.id = id;
        this.definition = definition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "ShortDefinition{" +
                "id=" + id +
                ", definition='" + definition + '\'' +
                '}';
    }
}

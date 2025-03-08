package app.first.myEng.engBoost.models.wordCard;

import jakarta.persistence.*;

@Entity
@Table(name = "examples")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "example")
    private String example;

    public Example() {
    }

    public Example(Integer id, String example) {
        this.id = id;
        this.example = example;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", example='" + example + '\'' +
                '}';
    }
}

package app.first.myEng.engBoost.api.merriamwebster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private String id;
    private String section;
    private List<String> stems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<String> getStems() {
        return stems;
    }

    public void setStems(List<String> stems) {
        this.stems = stems;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "id='" + id + '\'' +
                ", section='" + section + '\'' +
                ", stems=" + stems +
                '}';
    }
}

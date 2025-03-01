package app.first.myEng.engBoost.api.merriamwebster.model.definition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefinitionItem {
    @JsonProperty("sseq")
    private List<List<List<Object>>> senseSequence;

    public DefinitionItem() {
    }

    public List<List<List<Object>>> getSenseSequence() {
        return senseSequence;
    }

    public void setSenseSequence(List<List<List<Object>>> senseSequence) {
        this.senseSequence = senseSequence;
    }

    public String getTest() {
        return "Test field from DefinitionItem";
    }
}

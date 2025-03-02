package app.first.myEng.engBoost.api.merriamwebster.model;


import app.first.myEng.engBoost.api.merriamwebster.model.definition.DefinitionItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonItem {
    private Meta meta;
    @JsonProperty("fl")
    private String functionalLabel;
    @JsonProperty("def")
    private List<DefinitionItem> definitions;
    @JsonProperty("shortdef")
    private List<String> shortDefinitions;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getFunctionalLabel() {
        return functionalLabel;
    }

    public void setFunctionalLabel(String functionalLabel) {
        this.functionalLabel = functionalLabel;
    }

    public List<DefinitionItem> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DefinitionItem> definitions) {
        this.definitions = definitions;
    }

    public List<String> getShortDefinitions() {
        return shortDefinitions;
    }

    public void setShortDefinitions(List<String> shortDefinitions) {
        this.shortDefinitions = shortDefinitions;
    }

    @Override
    public String toString() {
        return "JsonItem{" +
                "meta=" + meta +
                ", functionalLabel='" + functionalLabel + '\'' +
                ", definitions=" + definitions +
                ", shortDefinitions=" + shortDefinitions +
                '}';
    }
}

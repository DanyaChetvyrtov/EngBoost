package app.first.myEng.engBoost.api.merriamwebster.model.definition;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VisText {
    @JsonProperty("t")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "VisText{" +
                "text='" + text + '\'' +
                '}';
    }
}

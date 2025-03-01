package app.first.myEng.engBoost.api.merriamwebster.model.definition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SenseItem {
    @JsonProperty("sn")
    private String senseNumber;
    private String type;
    @JsonProperty("dt")
    private List<List<Object>> definingText;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenseNumber() {
        return senseNumber;
    }

    public void setSenseNumber(String senseNumber) {
        this.senseNumber = senseNumber;
    }

    public List<List<Object>> getDefiningText() {
        return definingText;
    }

    public void setDefiningText(List<List<Object>> definingText) {
        this.definingText = definingText;
    }

    @Override
    public String toString() {
        return "SenseItem{" +
                "senseNumber='" + senseNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

package app.first.myEng.engBoost.api.merriamwebster.model.definition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"senseNumber", "type", "definingTexts"})
public class SenseItem {
    @JsonProperty("sn")
    private String senseNumber;
    private String type;
    @JsonProperty(value = "dt", access = JsonProperty.Access.WRITE_ONLY)
    private List<List<Object>> definingTextBeforeMap;
    private DefiningText definingTexts;

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

    public List<List<Object>> getDefiningTextBeforeMap() {
        return definingTextBeforeMap;
    }

    public void setDefiningTextBeforeMap(List<List<Object>> definingTextBeforeMap) {
        this.definingTextBeforeMap = definingTextBeforeMap;
    }

    public DefiningText getDefiningTexts() {
        return definingTexts;
    }

    public void setDefiningTexts(DefiningText definingTexts) {
        this.definingTexts = definingTexts;
    }

    @Override
    public String toString() {
        return "SenseItem{" +
                "senseNumber='" + senseNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

package app.first.myEng.engBoost.dto.wordCard;

import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.utils.WordTypeEntityDeserializer;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id", "word", "definition", "wordType"
})
public class WordCardShortDto {
    private Integer id;
    private String word;
    private String definition;

    @JsonDeserialize(using = WordTypeEntityDeserializer.class)
    private WordTypeEntity wordType;
}

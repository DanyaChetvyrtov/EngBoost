package app.first.myEng.engBoost.dto.wordCard;

import app.first.myEng.engBoost.dto.user.UserListItemDto;
import app.first.myEng.engBoost.dto.wordCard.wordComponents.ExampleDto;
import app.first.myEng.engBoost.dto.wordCard.wordComponents.ShortDefinitionDto;
import app.first.myEng.engBoost.dto.wordCard.wordComponents.StemDto;
import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.utils.WordTypeEntityDeserializer;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id", "word", "definition",
        "cardOwner", "wordType", "stems",
        "examples", "shortDefinitions"
})
public class WordCardDetailsDto {
    private Integer id;
    private String word;
    private String definition;

    @JsonDeserialize(using = WordTypeEntityDeserializer.class)
    private WordTypeEntity wordType;

    private List<StemDto> stems;
    private List<ExampleDto> examples;
    private List<ShortDefinitionDto> shortDefinitions;

    private UserListItemDto cardOwner;
}

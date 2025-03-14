package app.first.myEng.engBoost.dto.wordCard;

import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.utils.WordTypeEntityDeserializer;
import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCardClientDto {
    @NotNull(message = "Id can't be null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Word can't be null", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 2, max = 50, message = "Word should be in range between 2 and 50 chars",
            groups = {OnCreate.class, OnUpdate.class})
    private String word;
    @NotNull(message = "Definition must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 2, max = 100, message = "Definition should be in range between 2 and 250 chars",
            groups = {OnCreate.class, OnUpdate.class})
    private String definition;

    @JsonDeserialize(using = WordTypeEntityDeserializer.class)
    @NotNull(message = "Word type must be not null", groups = {OnCreate.class, OnUpdate.class})
    private WordTypeEntity wordType;

    private List<StemDto> stems;
    private List<ExampleDto> examples;
    private List<ShortDefinitionDto> shortDefinitions;

    @NotNull(message = "Card can't be without owner. Set userId", groups = {OnCreate.class, OnUpdate.class})
    private Integer userId;
}

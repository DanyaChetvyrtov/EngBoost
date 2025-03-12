package app.first.myEng.engBoost.dto.wordCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortDefinitionDto {
    private Integer id;

    private String definition;
}

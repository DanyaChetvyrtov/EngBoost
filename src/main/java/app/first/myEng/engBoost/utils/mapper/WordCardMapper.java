package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.wordCard.WordCardDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WordCardMapper {

    WordCardDto toDto(WordCard wordCard);
    WordCard toEntity(WordCardDto wordCardDto);

    default List<WordCardDto> toDtoList(List<WordCard> wordCards) {
        if (wordCards == null) {
            return Collections.emptyList();
        }
        return wordCards.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

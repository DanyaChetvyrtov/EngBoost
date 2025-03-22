package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.wordCard.WordCardDetailsDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardListItemDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardWriteDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface WordCardMapper extends MainMapper<WordCard, WordCardDto> {

    WordCardDto toDto(WordCard wordCard);

    WordCardListItemDto toShortDto(WordCard wordCard);

    WordCardDetailsDto toExtendedDto(WordCard wordCard);

    WordCard toEntity(WordCardDetailsDto wordCardDetailsDto);

    WordCard toEntity(WordCardWriteDto wordCardDto);

    default List<WordCardDto> toDtoList(List<WordCard> wordCards) {
        if (wordCards == null) {
            return Collections.emptyList();
        }
        return wordCards.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<WordCardListItemDto> toShortDtoList(List<WordCard> wordCards) {
        if (wordCards == null)
            return Collections.emptyList();

        return wordCards.stream()
                .map(this::toShortDto)
                .collect(Collectors.toList());
    }
}

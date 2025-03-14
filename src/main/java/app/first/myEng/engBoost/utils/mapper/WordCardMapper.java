package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.wordCard.WordCardClientDto;
import app.first.myEng.engBoost.dto.wordCard.WordCardExtendedDto;
import app.first.myEng.engBoost.models.wordCard.WordCard;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface WordCardMapper extends MainMapper<WordCard, WordCardExtendedDto> {

    WordCardExtendedDto toDto(WordCard wordCard);

    WordCard toEntity(WordCardExtendedDto wordCardExtendedDto);

    WordCard toEntity(WordCardClientDto wordCardDto);

    default List<WordCardExtendedDto> toDtoList(List<WordCard> wordCards) {
        if (wordCards == null) {
            return Collections.emptyList();
        }
        return wordCards.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}

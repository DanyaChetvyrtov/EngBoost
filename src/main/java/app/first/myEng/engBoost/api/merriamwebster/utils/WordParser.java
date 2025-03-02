package app.first.myEng.engBoost.api.merriamwebster.utils;

import app.first.myEng.engBoost.api.merriamwebster.model.JsonItem;
import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.DefinitionItem;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.SenseItem;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseWord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class WordParser {
    private static final Logger log = LoggerFactory.getLogger(WordParser.class);
    private final ObjectMapper objectMapper;

    public WordParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WordInfo parseWord(String json, String word) throws JsonProcessingException {
        List<JsonItem> jsonItems;
        try {
            jsonItems = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new FailToParseWord(e.getMessage());
        }

        JsonItem mainJsonItem = jsonItems.stream()
                .filter(item -> word.equals(item.getMeta().getId()))
                .findFirst().orElse(jsonItems.getFirst());

        List<DefinitionItem> definitions = mainJsonItem.getDefinitions();
//        List<List<Object>> senseSequences = definitions.getFirst().getSenseSequence()
//                .stream().map(List::getFirst).toList();

        List<List<Object>> senseSequences = definitions.stream()
                .findFirst().map(DefinitionItem::getSenseSequence)
                .orElse(Collections.emptyList()).stream().map(List::getFirst)
                .toList();

        List<SenseItem> senseItems = senseSequences.stream().map(obj -> {
            SenseItem senseItem = objectMapper.convertValue(obj.getLast(), SenseItem.class);
            senseItem.setType((String) obj.getFirst());
            return senseItem;
        }).toList();

        WordInfo wordInfo = new WordInfo();

        wordInfo.setWord(word);
        wordInfo.setWordMeta(mainJsonItem.getMeta());
        wordInfo.setWordType(mainJsonItem.getFunctionalLabel());
        wordInfo.setShortDef(mainJsonItem.getShortDefinitions());
        wordInfo.setSenseItems(senseItems);

        return wordInfo;
    }
}

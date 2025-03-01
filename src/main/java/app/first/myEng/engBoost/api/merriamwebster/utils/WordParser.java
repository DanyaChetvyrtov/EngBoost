package app.first.myEng.engBoost.api.merriamwebster.utils;

import app.first.myEng.engBoost.api.merriamwebster.model.JsonItem;
import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.DefinitionItem;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.SenseItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordParser {
    private final ObjectMapper objectMapper;

    public WordParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WordInfo parseWord(String json, String word) throws JsonProcessingException {
        List<JsonItem> jsonItems = objectMapper.readValue(json, new TypeReference<>() {});
        JsonItem mainJsonItem = jsonItems.getFirst();

        List<DefinitionItem> definitions = jsonItems.stream()
                .map(JsonItem::getDefinition)
                .toList().getFirst();

        List<Object> sseq = definitions.getFirst().getSenseSequence().getFirst().getFirst();
        SenseItem senseItem = objectMapper.convertValue(sseq.get(1), SenseItem.class);
        senseItem.setType((String) sseq.get(0));

        WordInfo wordInfo = new WordInfo();

        wordInfo.setWord(word);
        wordInfo.setWordMeta(mainJsonItem.getMeta());
        wordInfo.setWordType(mainJsonItem.getFunctionalLabel());
        wordInfo.setShortDef(mainJsonItem.getShortDefinitions());
        wordInfo.setSenseItem(senseItem);

        return wordInfo;
    }
}

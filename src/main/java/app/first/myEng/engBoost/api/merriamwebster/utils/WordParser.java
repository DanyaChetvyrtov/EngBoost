package app.first.myEng.engBoost.api.merriamwebster.utils;

import app.first.myEng.engBoost.api.merriamwebster.model.JsonItem;
import app.first.myEng.engBoost.api.merriamwebster.model.WordInfo;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.DefiningText;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.DefinitionItem;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.SenseItem;
import app.first.myEng.engBoost.api.merriamwebster.model.definition.VisText;
import app.first.myEng.engBoost.api.merriamwebster.model.exception.FailToParseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class WordParser {
    private final ObjectMapper objectMapper;

    public WordParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WordInfo parse(String json, String word) throws JsonProcessingException {
        List<JsonItem> jsonItems;
        try {
            jsonItems = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new FailToParseData(e.getMessage());
        }

        // Find more appropriate jsonItem
        JsonItem mainJsonItem = jsonItems.stream()
                .filter(item -> word.equals(item.getMeta().getId()))
                .findFirst().orElse(jsonItems.getFirst());

        List<DefinitionItem> definitions = mainJsonItem.getDefinitions();

        List<SenseItem> senseItems = definitions.stream()
                .findFirst().map(DefinitionItem::getSenseSequence)
                .orElse(Collections.emptyList()).stream().map(List::getFirst)
                .map(this::parseSenseItem).toList();

        return new WordInfo.Builder()
                .setWord(word)
                .setWordMeta(mainJsonItem.getMeta())
                .setWordType(mainJsonItem.getFunctionalLabel())
                .setShortDef(mainJsonItem.getShortDefinitions())
                .setSenseItems(senseItems)
                .build();
    }

    private DefiningText parseDefiningText(SenseItem senseItem) throws JsonProcessingException {
        DefiningText definingText = new DefiningText();
        senseItem.getDefiningTextBeforeMap().forEach(
                unmappedDefText -> {
                    if (unmappedDefText.getFirst().equals("text"))
                        definingText.setText((String) unmappedDefText.getLast());
                    else if (unmappedDefText.getFirst().equals("vis")) {
                        List<VisText> visTexts = objectMapper.convertValue(
                                unmappedDefText.getLast(), new TypeReference<>() {}
                        );
                        definingText.setVerbalIllustration(visTexts);
                    }
                }
        );
        return definingText;
    }

    private SenseItem parseSenseItem(List<Object> obj) {
        SenseItem senseItem = objectMapper.convertValue(obj.getLast(), SenseItem.class);
        senseItem.setType((String) obj.getFirst());

        DefiningText definingText = null;
        try {
            definingText = parseDefiningText(senseItem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        senseItem.setDefiningTexts(definingText);
        return senseItem;
    }
}

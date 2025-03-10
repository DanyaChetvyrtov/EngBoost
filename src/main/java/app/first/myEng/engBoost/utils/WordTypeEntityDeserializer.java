package app.first.myEng.engBoost.utils;

import app.first.myEng.engBoost.models.wordCard.WordTypeEntity;
import app.first.myEng.engBoost.models.wordCard.enumirate.WordType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class WordTypeEntityDeserializer extends JsonDeserializer<WordTypeEntity> {
    @Override
    public WordTypeEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {

        String type = jsonParser.getText();
        WordTypeEntity wordTypeEntity = new WordTypeEntity();
        wordTypeEntity.setWordType(WordType.valueOf(type));

        return wordTypeEntity;
    }
}

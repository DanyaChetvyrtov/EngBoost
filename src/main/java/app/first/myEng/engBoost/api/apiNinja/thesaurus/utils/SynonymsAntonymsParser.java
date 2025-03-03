package app.first.myEng.engBoost.api.apiNinja.thesaurus.utils;

import app.first.myEng.engBoost.api.apiNinja.thesaurus.model.WordSynonymsAntonyms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SynonymsAntonymsParser {
    private final ObjectMapper objectMapper;

    public SynonymsAntonymsParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WordSynonymsAntonyms parse(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }
}

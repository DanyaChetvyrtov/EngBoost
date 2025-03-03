package app.first.myEng.engBoost.api.apiNinja.thesaurus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WordSynonymsAntonyms {
    @JsonProperty("word")
    private String word;
    @JsonProperty("synonyms")
    private List<String> synonyms;
    @JsonProperty("antonyms")
    private List<String> antonyms;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    @Override
    public String toString() {
        return "WordSynonymsAntonyms{" +
                "synonyms='" + synonyms + '\'' +
                ", antonyms='" + antonyms + '\'' +
                '}';
    }
}

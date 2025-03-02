package app.first.myEng.engBoost.api.merriamwebster.model;

import app.first.myEng.engBoost.api.merriamwebster.model.definition.SenseItem;

import java.util.List;

public class WordInfo {
    private String word;
    private Meta wordMeta;
    private String wordType;
    private List<SenseItem> senseItems;
    private List<String> shortDef;

    public WordInfo() {
    }

    public WordInfo(String word, Meta wordMeta, String wordType, List<String> shortDef) {
        this.word = word;
        this.wordMeta = wordMeta;
        this.wordType = wordType;
        this.shortDef = shortDef;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Meta getWordMeta() {
        return wordMeta;
    }

    public void setWordMeta(Meta wordMeta) {
        this.wordMeta = wordMeta;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public List<String> getShortDef() {
        return shortDef;
    }

    public void setShortDef(List<String> shortDef) {
        this.shortDef = shortDef;
    }

    public List<SenseItem> getSenseItems() {
        return senseItems;
    }

    public void setSenseItems(List<SenseItem> senseItems) {
        this.senseItems = senseItems;
    }
}

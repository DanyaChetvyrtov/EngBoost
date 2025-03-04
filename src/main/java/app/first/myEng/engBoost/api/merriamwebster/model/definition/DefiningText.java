package app.first.myEng.engBoost.api.merriamwebster.model.definition;

import java.util.List;

public class DefiningText {
    private String text;
    private List<VisText> verbalIllustration;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<VisText> getVerbalIllustration() {
        return verbalIllustration;
    }

    public void setVerbalIllustration(List<VisText> verbalIllustration) {
        this.verbalIllustration = verbalIllustration;
    }

    @Override
    public String toString() {
        return "DefiningText{" +
                "text='" + text + '\'' +
                ", verbalIllustration='" + verbalIllustration + '\'' +
                '}';
    }
}

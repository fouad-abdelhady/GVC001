package hk.ust.cse.comp107x.gvc001.verb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Verb {

    private String arTranslation;
    private String auxiliaryVerb;
    private String details;
    private String enTranslation;
    private String more;
    private String spTranslation;
    private String verb;
    private int irregular;
    private HashMap<String, Conjugation> conjugationHashMap;
    private List<Conjugation> conjugation;


    public Verb() {
        conjugation = new ArrayList<>();
    }

    public String getArTranslation() {
        return arTranslation;
    }

    public void setArTranslation(String arTranslation) {
        this.arTranslation = arTranslation;
    }

    public String getAuxiliaryVerb() {
        return auxiliaryVerb;
    }

    public void setAuxiliaryVerb(String auxiliaryVerb) {
        this.auxiliaryVerb = auxiliaryVerb;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEnTranslation() {
        return enTranslation;
    }

    public void setEnTranslation(String enTranslation) {
        this.enTranslation = enTranslation;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getSpTranslation() {
        return spTranslation;
    }

    public void setSpTranslation(String spTranslation) {
        this.spTranslation = spTranslation;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public int getIrregular() {
        return irregular;
    }

    public void setIrregular(int irregular) {
        this.irregular = irregular;
    }

    public void addConjugation(Conjugation conjugation, String tense){
        this.conjugationHashMap.put(tense,conjugation);
    }

    public void setConjugation(List<Conjugation> conjugations){
        conjugation = conjugations;
    }
    public List<Conjugation> getConjugation(){
        return this.conjugation;
    }
}

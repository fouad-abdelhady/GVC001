package hk.ust.cse.comp107x.gvc001.verb;

public class Conjugation {
    public static final String SIMPLE_PRESENT = "0";
    public static final String SIMPLE_PAST = "1";
    public static final String SIMPLE_FUTUR = "2";
    public static final String PRESENT_PERFECT = "3";
    public static final String PAST_PERFECT = "4";
    public static final String FUTUR_PERFECT = "5";
    private String du;
    private String er;
    private String ich;
    private String ihr;
    private String sie;
    private String tense;
    private String wir;
    private int  verbId;

    public Conjugation() {

    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getIch() {
        return ich;
    }

    public void setIch(String ich) {
        this.ich = ich;
    }

    public String getIhr() {
        return ihr;
    }

    public void setIhr(String ihr) {
        this.ihr = ihr;
    }

    public String getSie() {
        return sie;
    }

    public void setSie(String sie) {
        this.sie = sie;
    }

    public String getTense() {
        return tense;
    }

    public void setTense(String tense) {
        this.tense = tense;
    }

    public int getVerbId() {
        return verbId;
    }

    public void setVerbId(int verbId) {
        this.verbId = verbId;
    }

    public String getWir() {
        return wir;
    }

    public void setWir(String wir) {
        this.wir = wir;
    }
}


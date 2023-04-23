package app.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Expose
    @SerializedName("english-sentence")
    private String englishSentence;

    @Expose
    @SerializedName("gopher-sentence")
    private String gopherSentence;

    public Sentence() {
    }

    public String getEnglishSentence() {
        return englishSentence;
    }

    public void setEnglishSentence(String englishSentence) {
        this.englishSentence = englishSentence;
    }

    public String getGopherSentence() {
        return gopherSentence;
    }

    public void setGopherSentence(String gopherSentence) {
        this.gopherSentence = gopherSentence;
    }
}

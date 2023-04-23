package app.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Expose
    @SerializedName("english-word")
    private String englishWord;

    @Expose
    @SerializedName("gopher-word")
    private String gopherWord;

    public Word() {
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getGopherWord() {
        return gopherWord;
    }

    public void setGopherWord(String gopherWord) {
        this.gopherWord = gopherWord;
    }
}

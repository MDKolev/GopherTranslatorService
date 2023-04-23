//package app.entity;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.io.Serializable;
//
//@Entity
//public class Translator implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Expose
//    @SerializedName("english-word")
//    private String englishWord;
//
//    @Expose
//    @SerializedName("english-sentence")
//    private String englishSentence;
//
//    @Expose
//    @SerializedName("gopher-word")
//    private String gopherWord;
//
//    @Expose
//    @SerializedName("gopher-sentence")
//    private String gopherSentence;
//
//    public Translator() {
//    }
//
//    public String getEnglishWord() {
//        return englishWord;
//    }
//
//    public void setEnglishWord(String englishWord) {
//        this.englishWord = englishWord;
//    }
//
//    public String getEnglishSentence() {
//        return englishSentence;
//    }
//
//    public void setEnglishSentence(String englishSentence) {
//        this.englishSentence = englishSentence;
//    }
//
//    public String getGopherWord() {
//        return gopherWord;
//    }
//
//    public void setGopherWord(String gopherWord) {
//        this.gopherWord = gopherWord;
//    }
//
//    public String getGopherSentence() {
//        return gopherSentence;
//    }
//
//    public void setGopherSentence(String gopherSentence) {
//        this.gopherSentence = gopherSentence;
//    }
//}

//package app.entity;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//public class User implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @SerializedName("english-word")
//    private String englishWord;
//
//    private String sentence;
//
//    public User() {
//    }
//
//    public User(String englishWord) {
//        this.englishWord = englishWord;
//    }
//
//    public User(String englishWord, String sentence) {
//        this.englishWord = englishWord;
//        this.sentence = sentence;
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
//    public String getSentence() {
//        return sentence;
//    }
//
//    public void setSentence(String sentence) {
//        this.sentence = sentence;
//    }
//}

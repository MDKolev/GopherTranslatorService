//package app.entity;
//
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//public class Gopher implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @SerializedName("gopher-word")
//    private String gopherWord;
//
//    @Column
//    private String sentence;
//
//    public Gopher() {
//    }
//
//    public Gopher(String gopherWord, String sentence) {
//        this.gopherWord = gopherWord;
//        this.sentence = sentence;
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
//    public String getSentence() {
//        return sentence;
//    }
//
//    public void setSentence(String sentence) {
//        this.sentence = sentence;
//    }
//}

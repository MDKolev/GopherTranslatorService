package app.service;

import app.entity.Translator;
import com.google.gson.Gson;

import java.util.Map;

public interface GopherService {

    String translateWord(String word);

    String translateSentence(String word);

    boolean isVowel(char charToCheck);

    String firstLetterToUpperCase(String wordToCorrect);
    String firstLetterToUpperCase(String wordToCorrect, boolean isVowel);

    String returnJsonOfWord(String englishWord);

    String returnJsonOfSentence(String englishSentence);

    Map<String, String> saveWords(Translator translator);



}

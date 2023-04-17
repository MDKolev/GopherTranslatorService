package app.service;

import app.entity.Translator;

import java.util.Map;

public interface GopherService {

    String translateWord(String word);

    String translateSentence(String word);

    boolean isVowel(char charToCheck);

    String firstLetterToUpperCase(String wordToCorrect, boolean isVowel);

    String returnJsonOfWord(String englishWord);

    String returnJsonOfSentence(String englishSentence);

    Map<String, String> saveWord(Translator translator);



}

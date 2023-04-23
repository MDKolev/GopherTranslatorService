package app.service;


import java.util.List;
import java.util.Map;

public interface TranslatorService { // nitpick for the name: TranslatorService

    String translateWord(String word);

    String translateSentence(String word);

    Map<String, List<Map<String,String>>> getHistory();

//    String firstLetterToUpperCase(String wordToCorrect, boolean isVowel);

//    Map<String, String> saveWord(String word);

}

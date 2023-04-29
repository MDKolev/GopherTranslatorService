package app.service;


import java.util.List;
import java.util.Map;

public interface TranslatorService {

    String translateWord(String word);

    String translateSentence(String word);

    Map<String, List<Map<String,String>>> getHistory();

}

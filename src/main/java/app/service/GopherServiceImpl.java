package app.service;

import app.entity.Translator;
import app.repository.GopherRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GopherServiceImpl implements GopherService {

    private final Translator translator;
    private final GopherRepository gopherRepository;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();


    @Autowired
    public GopherServiceImpl(Translator translator, GopherRepository gopherRepository) {
        this.translator = translator;
        this.gopherRepository = gopherRepository;
    }

    public String translateWord(String word) {
        boolean checker = false;

        translator.setEnglishWord(word);

        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                checker = true;

                if (isUpperCase(word)) {
                    word = "g" + word;
                    translator.setGopherWord(firstLetterToUpperCase(word,false));
                } else {
                    translator.setGopherWord("g" + word);
                }

            } else if (word.charAt(0) == 'x' || word.charAt(0) == 'X'
                    && word.charAt(1) == 'r' || word.charAt(1) == 'R') {
                checker = true;

                if (isUpperCase(word)) {
                    word = "ge" + word;
                    translator.setGopherWord(firstLetterToUpperCase(word,false));
                } else {
                    translator.setGopherWord("ge" + word);
                }
            }
            break;
        }

        if (!checker) {
            int index = 1;
            String prefix = "";
            String suffix = "";


            //checks for point 4
            for (int i = 0; i < word.length(); i++) {
                if (isVowel(word.charAt(i))) {
                    if (word.charAt(i) == 'u' && word.charAt(i - 1) == 'q') {
                        prefix = word.substring(0, i).toLowerCase() + word.charAt(i);
                        index = i + 1;
                        suffix = word.substring(index);

                        if (isUpperCase(word)) {
                            suffix = Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1);
                        }

                        translator.setGopherWord(suffix + prefix + "ogo");
                        break;
                    }
                }
            }

            //check for point 3
            if (index == 1) {
                for (int i = 0; i < word.length(); i++) {
                    if (isVowel(word.charAt(i))) {
                        prefix = word.substring(0, i).toLowerCase();
                        index = i;
                        break;
                    } else {
                        prefix = word.substring(0, i).toLowerCase();
                    }
                }
                suffix = word.substring(index);

                if (isUpperCase(word)) {
                    suffix = Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1);
                }
                translator.setGopherWord(suffix + prefix + "ogo");

                return translator.getGopherWord();
            }
        }

        return translator.getGopherWord();
    }

    public String translateSentence(String englishSentence) {
        List<String> rawSentence = Arrays.stream(englishSentence.split("\\s+")).toList();
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : rawSentence) {
            String converted = "";

            if (isUpperCase(string)) {
                converted = firstLetterToUpperCase(string,true);
                stringBuilder.append(converted);
            } else {
                stringBuilder.append(translateWord(string)).append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public String returnJsonOfWord(String englishWord) {

        Translator translator = new Translator();
        translator.setEnglishWord(englishWord);

        String translatedWord = translateWord(englishWord);
        translator.setGopherWord(translatedWord);

        saveWord(translator);

        return gson.toJson(translator);
    }

    public String returnJsonOfSentence(String englishSentence) {

        Translator translator = new Translator();
        translator.setEnglishSentence(englishSentence);

        String translatedSentence = translateSentence(translator.getEnglishSentence());
        translator.setGopherSentence(translatedSentence);

        saveSentence(translator);

        return gson.toJson(translator);
    }


    public boolean isVowel(char charToCheck) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

        for (char vowel : vowels) {
            if (vowel == charToCheck) {
                return true;
            }
        }
        return false;
    }

    public boolean isUpperCase(String word) {
        return Character.isUpperCase(word.charAt(0));
    }


    public String firstLetterToUpperCase(String wordToCorrect, boolean isSentence) {
        StringBuilder stringBuilder = new StringBuilder();
        String word = "";

        if (isSentence) {
            word = translateWord(wordToCorrect).toLowerCase();
        } else {
            word = wordToCorrect.toLowerCase();
        }

        stringBuilder
                .append(word.substring(0, 1).toUpperCase())
                .append(word.substring(1).toLowerCase())
                .append(" ");

        return stringBuilder.toString();
    }

    public Map<String, String> saveWord(Translator translator) {
        String englishWord = translator.getGopherWord();
        String gopherWord = translator.getGopherWord();

        Map<String, String> pairWords = new TreeMap<>();
        pairWords.put(englishWord,gopherWord);

        gopherRepository.save(translator);

        return pairWords;
    }

    public Map <String, String> saveSentence(Translator translator) {
        String englishSentence = translator.getEnglishSentence();
        String gopherSentence = translator.getGopherSentence();

        Map<String, String> pairSentences = new HashMap<>();
        pairSentences.put(englishSentence, gopherSentence);

        gopherRepository.save(translator);

        return pairSentences;

    }
}

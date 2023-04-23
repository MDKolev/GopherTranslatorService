package app.service;

import app.entity.Sentence;
import app.entity.Word;
import app.repository.SentenceRepository;
import app.repository.WordRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    private final WordRepository wordRepository;
    private final SentenceRepository sentenceRepository;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Autowired
    public TranslatorServiceImpl(WordRepository wordRepository, SentenceRepository sentenceRepository) {
        this.wordRepository = wordRepository;
        this.sentenceRepository = sentenceRepository;
    }

    public String translateWord(String word) {
        boolean checker = false;


        String translatedWord = null;

            if (isVowel(word.charAt(0))) {
                checker = true;

                if (isUpperCase(word)) {
                    word = "g" + word;
                    translatedWord = firstLetterToUpperCase(word, false);
                } else {
                    translatedWord = "g" + word;
                }

            } else if (word.charAt(0) == 'x' || word.charAt(0) == 'X'
                    && word.charAt(1) == 'r' || word.charAt(1) == 'R') {
                checker = true;

                if (isUpperCase(word)) {
                    word = "ge" + word;
                    translatedWord = firstLetterToUpperCase(word, false);
                } else {
                    translatedWord = "ge" + word;
                }
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

                        translatedWord = suffix + prefix + "ogo";
                        break;
                    }
                }
            }

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
                translatedWord = suffix + prefix +"ogo";

                return translatedWord;
            }
        }

        return translatedWord;
    }

    public String translateSentence(String englishSentence) {
        List<String> rawSentence = Arrays.stream(englishSentence.split("\\s+")).toList();
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : rawSentence) {
            String converted = "";

            if (isUpperCase(word)) {
                converted = firstLetterToUpperCase(word,true);
                stringBuilder.append(converted);
            } else {
                stringBuilder.append(translateWord(word)).append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public String returnJsonOfWord(String englishWord) {

        Word word = new Word();
        word.setEnglishWord(englishWord);

        String translatedWord = translateWord(englishWord);
        word.setGopherWord(translatedWord);

        wordRepository.save(word);

        return gson.toJson(word);
    }

    public String returnJsonOfSentence(String englishSentence) {

        Sentence sentence = new Sentence();
        sentence.setEnglishSentence(englishSentence);

        String translatedSentence = translateSentence(sentence.getEnglishSentence());
        sentence.setGopherSentence(translatedSentence);

        sentenceRepository.save(sentence);

        return gson.toJson(sentence);
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


    // firstLetterToUpperCaseIfWordOrElseTranslatedSentence
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

//    public Map<String, String> saveWord(Translator translator) {
//        String englishWord = translator.getGopherWord();
//        String gopherWord = translator.getGopherWord();
//
//        Map<String, String> pairWords = new TreeMap<>();
//        pairWords.put(englishWord,gopherWord);
//
//        gopherRepository.save(translator);
//
//        return pairWords;
//    }

//    public Map <String, String> saveSentence(Translator translator) {
//        String englishSentence = translator.getEnglishSentence();
//        String gopherSentence = translator.getGopherSentence();
//
//        Map<String, String> pairSentences = new HashMap<>();
//        pairSentences.put(englishSentence, gopherSentence);
//
//        gopherRepository.save(translator);
//
//        return pairSentences;
//
//    }
}

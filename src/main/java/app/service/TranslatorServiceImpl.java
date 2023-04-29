package app.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    private final Map<String, String> values;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public TranslatorServiceImpl(Map<String, String> values) {
        this.values = values;
    }

    public String translateWord(String englishWord) {
        boolean checker = false;

        String translatedWord = "";

        if (isVowel(englishWord.charAt(0))) {
            checker = true;

            //checks if a word starts with a vowel letter
            //adds prefix “g” to the word (ex. apple => gapple)
            if (isUpperCase(englishWord)) {
                englishWord = "g" + englishWord;
                translatedWord = firstLetterToUpperCase(englishWord, false);
            } else {
                translatedWord = "g" + englishWord;
            }

            //checks if a word starts with the consonant letters “xr”
            //adds the prefix “ge” to the begging of the word (ex. xray => gexray)
        } else if (englishWord.charAt(0) == 'x' || englishWord.charAt(0) == 'X'
                && englishWord.charAt(1) == 'r' || englishWord.charAt(1) == 'R') {
            checker = true;

            if (isUpperCase(englishWord)) {
                englishWord = "ge" + englishWord;
                translatedWord = firstLetterToUpperCase(englishWord, false);
            } else {
                translatedWord = "ge" + englishWord;
            }
            values.put(englishWord, translatedWord);
        }

        if (!checker) {
            int index = 1;
            String prefix = "";
            String suffix = "";

            //checks if a word starts with a consonant sound followed by "qu"
            //moves it to the end of the word and then adds "ogo" suffix to the word (e.g. "square" -> "aresquogo")
            for (int i = 0; i < englishWord.length(); i++) {
                if (isVowel(englishWord.charAt(i))) {
                    if (englishWord.charAt(i) == 'u' && englishWord.charAt(i - 1) == 'q') {
                        prefix = englishWord.substring(0, i).toLowerCase() + englishWord.charAt(i);
                        index = i + 1;
                        suffix = englishWord.substring(index);

                        if (isUpperCase(englishWord)) {
                            suffix = Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1);
                        }

                        translatedWord = suffix + prefix + "ogo";
                       values.put(englishWord, translatedWord);
                        break;
                    }
                }
            }

            if (index == 1) {
                //checks if a word starts with a consonant sound
                //moves it to the end of the word and then adds “ogo” suffix to the word (e.g. "chair" -> "airchogo”)
                for (int i = 0; i < englishWord.length(); i++) {
                    if (isVowel(englishWord.charAt(i))) {
                        prefix = englishWord.substring(0, i).toLowerCase();
                        index = i;
                        break;
                    } else {
                        prefix = englishWord.substring(0, i).toLowerCase();
                    }
                }
                suffix = englishWord.substring(index);

                if (isUpperCase(englishWord)) {
                    suffix = Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1);
                }
                translatedWord = suffix + prefix + "ogo";

               values.put(englishWord, translatedWord);
                return translatedWord;
            }
        }

        values.put(englishWord, translatedWord);

        return translatedWord;
    }

    public String translateSentence(String englishSentence) {
        List<String> rawSentence = Arrays.stream(englishSentence.split("\\s+")).toList();
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : rawSentence) {
            String converted = "";

            if (isUpperCase(word)) {
                converted = firstLetterToUpperCase(word, true);
                stringBuilder.append(converted);
            } else {
                stringBuilder.append(translateWord(word)).append(" ");
            }
        }

        values.put(englishSentence, stringBuilder.toString());

        return stringBuilder.toString();
    }

    @Override
    public Map<String, List<Map<String, String>>> getHistory() {
        List<Map<String, String>> historyList = new ArrayList<>();

        for (Map.Entry<String, String> entry : values.entrySet()) {
            Map<String, String> wordMap = new TreeMap<>();
            wordMap.put(entry.getKey(), entry.getValue());
            historyList.add(wordMap);
        }

        Map<String, List<Map<String, String>>> result = new TreeMap<>();
        historyList.sort(Comparator.comparing(m -> m.keySet().iterator().next()));

        result.put("history", historyList);
        return result;

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
    // TODO: separate method
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

}

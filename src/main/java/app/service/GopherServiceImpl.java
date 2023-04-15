package app.service;

import app.entity.Translator;
import app.repository.GopherRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class GopherServiceImpl implements GopherService {


    private final Translator translator;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private final GopherRepository gopherRepository;

//    @Autowired
    public GopherServiceImpl(Translator translator, GopherRepository gopherRepository) {
        this.translator = translator;
        this.gopherRepository = gopherRepository;
    }

    public String translateWord(String word) {
        boolean checker = false;

        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        StringBuilder stringBuilder = new StringBuilder();

        for (char vowel : vowels) {
            if (vowel == word.charAt(0)) {
                checker = true;
                word = "g" + word;
                translator.setGopherWord(word);
            } else if (word.charAt(0) == 'x' && word.charAt(1) == 'r') {
                checker = true;
                word = "ge" + word;
                translator.setGopherWord(word);
            }
        }

        if (!checker) {
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < vowels.length; j++) {

                    while (word.charAt(i) != vowels[j]) {
                        stringBuilder.append(word.charAt(i));
                        word.charAt(i++);
                        vowels[i] = vowels[i + 1];
                    }

                    String subsequence = word.subSequence(i, word.length()).toString();
                    String gopherWord = subsequence + stringBuilder + "ogo";

                    translator.setGopherWord(gopherWord);
                    break;
                }
                break;
            }
        }
        return translator.getGopherWord();
    }

    @Override
    public String returnJson(String englishWord) {

        Translator translator = new Translator();
        translator.setEnglishWord(englishWord);

        String translatedWord = translateWord(englishWord);
        translator.setGopherWord(translatedWord);

        return gson.toJson(translator);

    }
}

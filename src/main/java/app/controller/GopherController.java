package app.controller;

import app.service.TranslatorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@RestController
//@ResponseBody
public class GopherController {

    // should segregate responsibilities in the Controller -> Service -> Repository pattern
    // findWordsQuery / selectWordsQuery
    private final String QUERY_FOR_WORDS = "SELECT english_word, gopher_word " +
                                           "FROM translator " +
                                           "WHERE english_word IS NOT NULL " +
                                           "ORDER BY english_word ASC";


    private final String QUERY_FOR_SENTENCES = "SELECT english_sentence, gopher_sentence " +
                                               "FROM translator " +
                                               "WHERE english_sentence IS NOT NULL " +
                                               "ORDER BY english_sentence ASC";
    @Autowired
    private TranslatorService translatorService;

    @Autowired
    private Gson gson;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Must return JSON! Also applies to everything else
    @PostMapping("/word/{wordToTranslate}")
    @Produces(MediaType.APPLICATION_JSON)
    public String translateWord(@PathVariable String wordToTranslate) {
//        String word = gopherService.translateWord();
        // Marshal word to JSON
        // Return the result
        return translatorService.returnJsonOfWord(wordToTranslate);
    }

    @PostMapping("/sentence/{sentenceToTranslate}")
    public String translateSentence(@PathVariable String sentenceToTranslate) {
        return translatorService.returnJsonOfSentence(sentenceToTranslate);
    }

    @GetMapping("/history/words")
    public List<Map<String, Object>> getHistoryOfWords() {
        return jdbcTemplate.queryForList(QUERY_FOR_WORDS);
    }

    @GetMapping("/history/sentences")
    public List<Map<String, Object>> getHistoryOfSentences() {
        return jdbcTemplate.queryForList(QUERY_FOR_SENTENCES);
    }

    @GetMapping("/history/all")
    public Map<String, Map<String, String>> getAllHistory() {
        Map<String, Map<String, String>> history = new TreeMap<>();
        Map<String, String> mapOfResult = new TreeMap<>();

        jdbcTemplate.query(QUERY_FOR_WORDS, rs -> {
            String englishWord = rs.getString("english_word");
            String gopherWord = rs.getString("gopher_word");

            mapOfResult.put(englishWord, gopherWord);
        });

        jdbcTemplate.query(QUERY_FOR_SENTENCES, rs -> {
            String englishSentence = rs.getString("english_sentence");
            String gopherSentence = rs.getString("gopher_sentence");

            mapOfResult.put(englishSentence, gopherSentence);
        });

        history.put("history", mapOfResult);

        return history;

    }
}

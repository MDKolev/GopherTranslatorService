package app.controller;

import app.entity.Word;
import app.service.TranslatorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
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
    @RequestMapping(path = "/word",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public Map<String, String> translateWord(@RequestBody Map<String,String> request) {
//        String word = gopherService.translateWord();
        // Marshal word to JSON
        // Return the result
        String englishWord = request.get("english-word");
        String gopherWord = translatorService.translateWord(englishWord);

        Map<String, String> response = new HashMap<>();
        response.put("gopher-word", gopherWord);

        return response;
    }

    @RequestMapping(path = "/sentence",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public Map<String, String> translateSentence(@RequestBody Map<String, String> request) {
        String englishSentence = request.get("english-sentence");
        String gopherSentence = translatorService.translateSentence(englishSentence);

        Map<String, String> response = new HashMap<>();
        response.put("gopher-sentence", gopherSentence);

        return response;
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

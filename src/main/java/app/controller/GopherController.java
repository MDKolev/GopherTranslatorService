package app.controller;

import app.service.TranslatorService;
import com.google.gson.Gson;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.rmi.MarshalException;
import java.util.*;

@RestController
//@ResponseBody
public class GopherController {

    // should segregate responsibilities in the Controller -> Service -> Repository pattern
    // findWordsQuery / selectWordsQuery

    @Autowired
    private TranslatorService translatorService;

    @Autowired
    private Gson gson;

//    private History history;

    // Must return JSON! Also applies to everything else
    @RequestMapping(path = "/word",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public Map<String, String> translateWord(@RequestBody Map<String, String> request) {
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

    @GetMapping("/history")
    public Map<String, List<Map<String, String>>> getHistoryOfWords() {
        return translatorService.getHistory();
//
//    @GetMapping("/history/sentences")
//    public List<Map<String, Object>> getHistoryOfSentences() {
//        return sentenceRepository.findAllSentences();
    }

//    @GetMapping("/history/all")
//    public Map<String, Map<String, String>> getAllHistory() {
//        Map<String, Map<String, String>> history = new TreeMap<>();
//        Map<String, String> mapOfResult = new TreeMap<>();
//
//        jdbcTemplate.query(QUERY_FOR_WORDS, rs -> {
//            String englishWord = rs.getString("english_word");
//            String gopherWord = rs.getString("gopher_word");
//
//            mapOfResult.put(englishWord, gopherWord);
//        });
//
//        jdbcTemplate.query(QUERY_FOR_SENTENCES, rs -> {
//            String englishSentence = rs.getString("english_sentence");
//            String gopherSentence = rs.getString("gopher_sentence");
//
//            mapOfResult.put(englishSentence, gopherSentence);
//        });
//
//        history.put("history", mapOfResult);
//
//        return history;
//
//    }
//    }
}

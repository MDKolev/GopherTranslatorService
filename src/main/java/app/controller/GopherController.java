package app.controller;

import app.entity.Translator;
import app.service.GopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class GopherController {

    @Autowired
    private GopherService gopherService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/word/{wordToTranslate}")
//    @Produces(MediaType.APPLICATION_JSON)
    public String translateWord(@PathVariable String wordToTranslate) {
        return this.gopherService.returnJsonOfWord(wordToTranslate);

    }

    @PostMapping("/sentence/{sentenceToTranslate}")
    public String translateSentence(@PathVariable String sentenceToTranslate) {
        return this.gopherService.returnJsonOfSentence(sentenceToTranslate);
    }

    @GetMapping("/history/words")
    public List<Map<String, Object>> getHistoryOfWords() {
      String query = "SELECT english_word, gopher_word FROM translator ORDER BY english_word ASC";

      return jdbcTemplate.queryForList(query);
    }

    @GetMapping("/history/sentences")
    public List<Map<String, Object>> getHistoryOfSentences() {
      String query = "SELECT english_sentence, gopher_sentence FROM translator ORDER BY english_sentence ASC";

      return jdbcTemplate.queryForList(query);
    }

    @GetMapping("/history/all")
    public ResponseEntity<List<Translator>> getFullHistory() {
        List<Translator> gophers = jdbcTemplate.query("SELECT * FROM gophers ORDER BY english_word ASC",
                (resultSet, i) -> new Translator(
                        resultSet.getString("gopher_word"),
                        resultSet.getString("english_word"),
                        resultSet.getString("gopher_sentence"),
                        resultSet.getString("english_sentence")
                )
        );
        return new ResponseEntity<>(gophers, HttpStatus.OK);
    }


}

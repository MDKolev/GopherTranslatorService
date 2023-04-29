package app.controller;

import app.service.TranslatorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.*;

@RestController
public class GopherController {

    @Autowired
    private TranslatorService translatorService;

    @Autowired
    private Gson gson;

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
    }
}

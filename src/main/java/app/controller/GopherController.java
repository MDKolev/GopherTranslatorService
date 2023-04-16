package app.controller;

import app.entity.Translator;
import app.service.GopherService;
import org.hibernate.engine.transaction.jta.platform.internal.TransactionManagerBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableRowSorter;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
@ResponseBody
public class GopherController {

    @Autowired
    private GopherService gopherService;

    @PostMapping("/word/{wordToTranslate}")
//    @Produces(MediaType.APPLICATION_JSON)
    public String translateWord(@PathVariable String wordToTranslate) {
        return this.gopherService.returnJsonOfWord(wordToTranslate);

    }

    @PostMapping("/sentence/{sentenceToTranslate}")
    public String translateSentence(@PathVariable String sentenceToTranslate) {
        return this.gopherService.returnJsonOfSentence(sentenceToTranslate);
    }

//    @GetMapping("/word")
//    public ResponseEntity<String> getWord(@ResponseBody Translator translator) {
//        return gopherService.getHistory(translator);
//    }

}

package app.controller;

import app.service.GopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@ResponseBody
public class GopherController {

    @Autowired
    private GopherService gopherService;

    @PostMapping("/word/{wordToTranslate}")
//    @Produces(MediaType.APPLICATION_JSON)
    public String translate(@PathVariable String wordToTranslate) {

        return this.gopherService.returnJson(wordToTranslate);
    }


}

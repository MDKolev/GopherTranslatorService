package app.controller;

import app.service.TranslatorService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GopherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TranslatorService translatorService;

    @Autowired
    private Gson gson;

    @Test
    public void testTranslateWord() throws Exception {
        String englishWord = "hello";
        String gopherWord = "ellohogo";

        Map<String, String> request = new HashMap<>();
        request.put("english-word", englishWord);
        mockMvc.perform(MockMvcRequestBuilders.post("/word")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gopher-word").value(gopherWord));
    }

    @Test
    public void testTranslateSentence() throws Exception {
        String englishSentence = "hello world";
        String gopherSentence = "ellohogo orldwogo";

        Map<String, String> request = new HashMap<>();
        request.put("english-sentence", englishSentence);

        mockMvc.perform(MockMvcRequestBuilders.post("/sentence")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gopher-sentence").value(gopherSentence));
    }
}

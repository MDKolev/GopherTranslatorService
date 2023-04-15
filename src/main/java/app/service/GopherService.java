package app.service;

import com.google.gson.Gson;

public interface GopherService {

    String translateWord(String word);

    String returnJson(String englishWord);
}

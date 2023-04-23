//package app.entity;
//
//
//import java.util.*;
//public class History {
//
//    private Map<String, String> history;
//
//    private  List<Map<String, String>> historyList;
//
//    public void put(String englishWord, String gopherWord) {
//        history.put(englishWord, gopherWord);
//    }
//
//
//    public Map<String, List<Map<String,String>>> getHistory() {
////        List<Map<String, String>> historyList = new ArrayList<>();
//        for (Map.Entry<String, String> entry : history.entrySet()) {
//            Map<String, String> wordMap = new HashMap<>();
//            wordMap.put(entry.getKey(), entry.getValue());
//            historyList.add(wordMap);
//        }
//        Map<String, List<Map<String, String>>> result = new HashMap<>();
//        result.put("history", historyList);
//        return result;
//    }
//
//
//
//
//}

package app.service;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TranslatorServiceImplTest {

    @Mock
    private Map<String, String> mockValues;

    @InjectMocks
    private TranslatorServiceImpl translatorService;

    @Test
    public void testTranslateWordShouldReturnCorrectTranslation() {
        String englishWord = "apple";
        String expectedTranslatedWord = "gapple";

        when(mockValues.put(anyString(), anyString())).thenReturn(null);

        String translatedWord = translatorService.translateWord(englishWord);

        verify(mockValues).put(englishWord, expectedTranslatedWord);
        assertEquals(expectedTranslatedWord, translatedWord);
    }

    @Test
    public void testTranslateSentenceShouldReturnCorrectTranslation() {
        String englishSentence = "The quick brown fox jumps over the lazy dog";
        String expectedTranslatedSentence = "Ethogo ickquogo ownbrogo oxfogo umpsjogo gover ethogo azylogo ogdogo";

        when(mockValues.put(anyString(), anyString())).thenReturn(null);

        String translatedSentence = translatorService.translateSentence(englishSentence);

        verify(mockValues).put(englishSentence, expectedTranslatedSentence);
        assertEquals(expectedTranslatedSentence, translatedSentence);
    }

    @Test
    public void testGetHistory() {
        Map<String, String> mockValuesCopy = new HashMap<>();
        mockValuesCopy.put("apple", "gapple");
        mockValuesCopy.put("orange", "gorange");

        when(mockValues.entrySet()).thenReturn(mockValuesCopy.entrySet());

        Map<String, List<Map<String, String>>> history = translatorService.getHistory();

        assertEquals(2, history.get("history").size());

        List<Map<String, String>> expectedHistoryList = new ArrayList<>();
        Map<String, String> appleEntry = new TreeMap<>();
        appleEntry.put("apple", "gapple");
        Map<String, String> orangeEntry = new TreeMap<>();
        orangeEntry.put("orange", "gorange");
        expectedHistoryList.add(appleEntry);
        expectedHistoryList.add(orangeEntry);

        assertEquals(expectedHistoryList, history.get("history"));
    }

}

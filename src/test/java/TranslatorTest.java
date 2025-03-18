import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.model.TranslationPair;
import ru.myapp.repository.TranslationRepository;
import ru.myapp.service.TranslatorService;
import space.dynomake.libretranslate.Language;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TranslatorTest {
    @Mock
    private TranslationRepository repository;
    @InjectMocks
    private TranslatorService service;
    private static Language russian = Language.RUSSIAN;
    private static Language english = Language.ENGLISH;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTranslateText_WhenTextIsEmpty() {
        String text = "";
        Exception ex = assertThrows(TranslationException.class, () -> {
            service.translateText(text, russian, english);
        });
        assertEquals("Текстовое поле не может быть пустым", ex.getMessage());
    }

    @Test
    void testTranslateText_WhenTextIsNotCyryllic() {
        String text = "Текст для test";
        Exception ex = assertThrows(ValidationException.class, () -> {
            service.translateText(text, russian, english);
        });
        assertEquals("Текст должен содержать только буквы русского алфавита и допустимые знаки ,.!?", ex.getMessage());
    }

    @Test
    void testTranslateText_WhenTextIsNotLatin() {
        String text = "Текст для test";
        Exception ex = assertThrows(ValidationException.class, () -> {
            service.translateText(text, english, russian);
        });
        assertEquals("Текст должен содержать только буквы латинского алфавита и допустимые знаки ,.!?", ex.getMessage());
    }

    @Test
    void testAddTranslation() {
        String source = "тест";
        String translate = "test";
        TranslationPair pair = new TranslationPair(source, translate);
        service.addTranslation(source, translate);
        verify(repository, times(1)).addTranslation(pair); // Проверяем, что метод addTranslation вызван с правильным аргументом
    }

    @Test
    void testSearchTranslate_WhenTranslationExists() {
        String source = "test";
        TranslationPair pair = new TranslationPair(source, "тест");
        when(repository.getTranslation(source)).thenReturn(pair);
        TranslationPair result = service.searchTranslate(source);
        assertNotNull(result);
        assertEquals(pair, result);
        verify(repository, times(1)).getTranslation(source);
    }

    @Test
    void testSearchTranslate_WhenTranslationDoesNotExist() {
        String source = "тест";
        when(repository.getTranslation(source)).thenReturn(null);
        TranslationPair result = service.searchTranslate(source);
        assertNull(result);
        verify(repository, times(1)).getTranslation(source);
    }

    @Test
    void testGetAllTranslateSortedBySource() {
        TranslationPair pair1 = new TranslationPair("тест1", "test1");
        TranslationPair pair2 = new TranslationPair("тест2", "test2");
        List<TranslationPair> sortedList = Arrays.asList(pair1, pair2);
        when(repository.getAllSortedBySource()).thenReturn(sortedList);
        List<TranslationPair> result = service.getAllTranslateSortedBySource();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(sortedList, result);
        verify(repository, times(1)).getAllSortedBySource();
    }

    @Test
    void testGetAllTranslateSortedBySource_WhenRepositoryIsEmpty() {
        when(repository.getAllSortedBySource()).thenReturn(Collections.emptyList());
        List<TranslationPair> result = service.getAllTranslateSortedBySource();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).getAllSortedBySource();
    }
}



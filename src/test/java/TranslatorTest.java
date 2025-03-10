import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.service.TranslatorService;
import space.dynomake.libretranslate.Language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TranslatorTest {
    private static TranslatorService service;
    private static Language russian;
    private static Language english;

    @BeforeAll
    static void createService() {
        service = new TranslatorService();
        russian = Language.RUSSIAN;
        english = Language.ENGLISH;
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
}

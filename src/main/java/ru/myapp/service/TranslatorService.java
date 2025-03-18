package ru.myapp.service;

import lombok.extern.slf4j.Slf4j;
import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.model.TranslationPair;
import ru.myapp.repository.TranslationRepository;
import ru.myapp.validation.Validator;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

import java.util.List;

@Slf4j
public class TranslatorService {
    private final TranslationRepository repository;

    public TranslatorService(TranslationRepository repository) {
        this.repository = repository;
    }

    public String translateText(String text, Language source, Language target) throws ValidationException, TranslationException {
        if (Validator.isNullOrEmpty(text)) {
            log.error("Ошибка валидации.Текстовое поле не может быть пустым");
            throw new TranslationException("Текстовое поле не может быть пустым");
        }
        if (source == Language.RUSSIAN && Validator.containsLatinAndSpecialChar(text)) {
            log.error("Ошибка валидации.Текст должен содержать только буквы русского алфавита и допустимые знаки ,.!?");
            throw new ValidationException("Текст должен содержать только буквы русского алфавита и допустимые знаки ,.!?");
        } else if (Validator.containsCyrillicAndSpecialChar(text)) {
            log.error("Ошибка валидации.Текст должен содержать только буквы латинского алфавита и допустимые знаки ,.!?");
            throw new ValidationException("Текст должен содержать только буквы латинского алфавита и допустимые знаки ,.!?");
        }
        return Translator.translate(source, target, text);
    }

    public void addTranslation(String source, String translate) {
        log.info("Добавление перевода: " + source + " -> " + translate);
        TranslationPair pair = new TranslationPair(source, translate);
        repository.addTranslation(pair);
        log.info("Метод addTranslation вызван в repository");
    }

    public TranslationPair searchTranslate(String source) {
        return repository.getTranslation(source);
    }

    public List<TranslationPair> getAllTranslateSortedBySource() {
        return repository.getAllSortedBySource();
    }
}

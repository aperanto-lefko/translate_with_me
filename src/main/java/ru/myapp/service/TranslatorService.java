package ru.myapp.service;

import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.validation.Validator;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

public class TranslatorService {
    public String translateText(String text, Language source, Language target) throws ValidationException, TranslationException {
       if(Validator.isNullOrEmpty(text)) {
           throw new TranslationException("Текстовое поле не может быть пустым");
       }
        if(source == Language.RUSSIAN) {
            if(Validator.containsLatinAndSpecialChar(text)) {
                throw new ValidationException("Текст должен содержать только буквы русского алфавита и допустимые знаки ,.!?");
            }
        } else {
            if(Validator.containsCyrillicAndSpecialChar(text)){
                throw new ValidationException("Текст должен содержать только буквы латинского алфавита и допустимые знаки ,.!?");
            }
        }
        return Translator.translate(source, target, text);
    }
}

package ru.myapp.validation;

import org.apache.commons.lang3.StringUtils;

public class Validator {
    public static boolean containsCyrillicAndSpecialChar(String text) {

        return !text.matches("[a-zA-Z ,.!?]*");
    }

    public static boolean containsLatinAndSpecialChar(String text) {

        return !text.matches("[а-яА-ЯЁё ,.!?]*");
    }

    public static boolean isNullOrEmpty(String text) {
        return StringUtils.isBlank(text);
    }
}

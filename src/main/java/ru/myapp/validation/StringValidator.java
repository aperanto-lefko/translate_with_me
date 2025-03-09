package ru.myapp.validation;

public class StringValidator {
    public static boolean containsCyrillicAndSpecialChar(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        return !text.matches("[a-zA-Z ,.!?]*");
    }
    public static boolean containsLatinAndSpecialChar(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        return !text.matches("[а-яА-ЯЁё ,.!?]*");
    }
}

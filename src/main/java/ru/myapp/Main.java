package ru.myapp;

import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

public class Main {
    public static void main(String[] args) {
        System.out.println(Translator.translate(Language.RUSSIAN, Language.CHINESE, "Для примера, переведем текст с русского на английский и выведем в консоль:"));

    }
}
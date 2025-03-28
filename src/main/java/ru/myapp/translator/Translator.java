package ru.myapp.translator;

import space.dynomake.libretranslate.Language;

public class Translator {

    public static String translate(Language source, Language target, String text) {
        return space.dynomake.libretranslate.Translator.translate(source, target, text);
    }
}


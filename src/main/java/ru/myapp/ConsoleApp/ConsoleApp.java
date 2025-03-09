package ru.myapp.ConsoleApp;

import space.dynomake.libretranslate.Language;

import javax.sound.midi.Soundbank;
import java.lang.invoke.LambdaConversionException;
import java.util.Scanner;

public class ConsoleApp {

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Вас приветствует приложение для перевода. Выберите язык для исходного текста" +
                    "1. Русский" +
                    "2. Английский" +
                    "3. Испанский");
            Language sourceLanguage = switch (scanner.nextLine()) {
                case "1" -> Language.RUSSIAN;
                case "2" -> Language.ENGLISH;
                case "3" -> Language.SPANISH;
                default -> {
                    System.out.println("Неверный выбор. По умолчанию выбран русский язык");
                    yield Language.RUSSIAN;
                }
            };
        }
    }
}


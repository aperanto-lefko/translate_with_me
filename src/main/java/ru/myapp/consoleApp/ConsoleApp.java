package ru.myapp.consoleApp;

import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.service.TranslatorService;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

import java.util.Scanner;

public class ConsoleApp {

    public static void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Выберите язык для исходного текста:" +
                    "\n 1. Русский" +
                    "\n 2. Английский" +
                    "\n 3. Испанский");
            Language sourceLanguage = chooseLanguage(scanner.nextLine());
            System.out.println("Выберите целевой язык:" +
                    "\n 1. Русский" +
                    "\n 2. Английский" +
                    "\n 3. Испанский");
            Language targetLanguage = chooseLanguage(scanner.nextLine());
            System.out.println("Введите текст для перевода");
            String text = scanner.nextLine();
            TranslatorService service = new TranslatorService();
            System.out.println(service.translateText(text, sourceLanguage, targetLanguage));
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } catch (TranslationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Language chooseLanguage(String text) {
        return switch (text) {
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



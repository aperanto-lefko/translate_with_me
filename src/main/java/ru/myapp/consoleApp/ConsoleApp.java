package ru.myapp.consoleApp;

import lombok.extern.slf4j.Slf4j;
import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.service.TranslatorService;
import space.dynomake.libretranslate.Language;

import java.util.Scanner;

@Slf4j
public class ConsoleApp {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Language sourceLanguage = chooseLanguage(scanner, "исходного");
                log.info("Выбран язык для перевода: {}", sourceLanguage);
                Language targetLanguage = chooseLanguage(scanner, "целевого");
                log.info("Выбран язык для перевода: {}", targetLanguage);
                while (true) {
                    System.out.println("Введите текст для перевода или нажмите 0, чтобы выйти");
                    String text = scanner.nextLine();
                    if (text.equals("0")) {
                        return;
                    }
                    try {
                        TranslatorService service = new TranslatorService();
                        System.out.println(service.translateText(text, sourceLanguage, targetLanguage));

                    } catch (ValidationException | TranslationException ex) {
                        log.error("Ошибка валидации: {}", ex.getMessage());
                        System.out.println(ex.getMessage());
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Непредвиденная ошибка: " + ex.getMessage());
                log.error("Непредвиденная ошибка: {}", ex.getMessage());
            }
        }
    }


    private static Language chooseLanguage(Scanner scanner, String type) {
        System.out.println("Выберите язык для " + type + " текста " +
                "\n 1. Русский" +
                "\n 2. Английский" +
                "\n 3. Испанский");
        return switch (scanner.nextLine()) {
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



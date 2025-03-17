package ru.myapp.consoleApp;

import lombok.extern.slf4j.Slf4j;
import ru.myapp.exception.TranslationException;
import ru.myapp.exception.ValidationException;
import ru.myapp.model.TranslationPair;
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

                TranslatorService service = new TranslatorService();

                while (true) {
                    try {
                        System.out.println("\n 1. Ввести текст для перевода" +
                                "\n 2. Показать все слова в словаре" +
                                "\n 3. Найти слово в словаре" +
                                "\n 0. Выход");

                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1" -> {
                                System.out.println("Введите текст для перевода:");
                                String source = scanner.nextLine();
                                String translate = service.translateText(source, sourceLanguage, targetLanguage);
                                System.out.println("Перевод: " + translate);


                                System.out.println("\n 1. Занести пару в словарь" +
                                        "\n 2. Перевести другое слово");

                                String postTranslateChoice = scanner.nextLine();
                                switch (postTranslateChoice) {
                                    case "1" -> {
                                        service.addTranslation(source, translate);
                                        System.out.println("Перевод добавлен в словарь");
                                    }
                                    case "2" -> {
                                    }
                                    default -> System.out.println("Некорректный ввод");
                                }
                            }
                            case "2" -> {
                                service.getAllTranslateSortedBySource()
                                        .forEach(System.out::println);
                            }
                            case "3" -> {
                                System.out.println("Введите слово для поиска:");
                                String search = scanner.nextLine();
                                TranslationPair pair = service.searchTranslate(search);
                                System.out.println(pair != null ? pair : "Перевод не найден");
                            }
                            case "0" -> {
                                System.out.println("Выход из программы");
                                return;
                            }
                            default -> System.out.println("Некорректный ввод");
                        }
                    } catch (ValidationException | TranslationException ex) {
                        log.error("Ошибка валидации: {}: {}", ex.getClass(), ex.getMessage());
                        System.out.println(ex.getMessage());
                    } catch (Exception ex) {

                        System.out.println("Непредвиденная ошибка: " + ex.getMessage());
                        log.error("Непредвиденная ошибка: {}: {}", ex.getClass(), ex.getMessage());
                    }
                }
            } catch (Exception ex) {

                System.out.println("Ошибка: " + ex.getMessage());
                log.error("Ошибка: {}: {}", ex.getClass(), ex.getMessage());
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



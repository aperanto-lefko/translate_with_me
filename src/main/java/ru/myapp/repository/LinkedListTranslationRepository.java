package ru.myapp.repository;

import ru.myapp.repository.LinkedList.CustomLinkedList;

public class LinkedListTranslationRepository implements TranslationRepository{
    private CustomLinkedList<String[]> translations;
}

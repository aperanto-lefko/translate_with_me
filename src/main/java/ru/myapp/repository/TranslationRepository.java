package ru.myapp.repository;


import ru.myapp.model.TranslationPair;

import java.util.List;


public interface TranslationRepository {
    void addTranslation(TranslationPair pair);

    TranslationPair getTranslation(String original);
    void sortBySource();
    List<TranslationPair> getAllSortedBySource();
    }

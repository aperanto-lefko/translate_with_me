package ru.myapp.repository;

import ru.myapp.model.TranslationPair;
import ru.myapp.repository.ArrayList.CustomArrayList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ArrayListTranslationRepository implements TranslationRepository {
    private final CustomArrayList<TranslationPair> storage;

    public ArrayListTranslationRepository() {
        this.storage = new CustomArrayList<>();
    }

    @Override
    public void addTranslation(TranslationPair pair) {
        storage.add(pair);
    }

    @Override
    public TranslationPair getTranslation(String source) {
        for (int i = 0; i < storage.size(); i++) {
            TranslationPair pair = storage.get(i);
            if (pair.getSource().equalsIgnoreCase(source)) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public List<TranslationPair> getAllSortedBySource() {
        sortBySource();
        List<TranslationPair> list = new ArrayList<>();
        for (int i = 0; i < storage.size(); i++) {
            list.add(storage.get(i));
        }
        return list;
    }

    @Override
    public void sortBySource() {
        storage.mergeSort(Comparator.comparing(TranslationPair::getSource));
    }


}

package ru.myapp.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TranslationPair {
    String source;
    String translate;

    @Override
    public String toString() {
        return source + "->" + translate;
    }
}

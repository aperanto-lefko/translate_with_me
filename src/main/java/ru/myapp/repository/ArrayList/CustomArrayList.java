package ru.myapp.repository.ArrayList;

import java.util.Arrays;
import java.util.Comparator;

public class CustomArrayList<T> {
    private Object[] elements; //внутренний массив для хранения элементов
    private int size; // кол-во элементов в списке
    private static final int DEFAULT_INT_CAPACITY = 10;
    public CustomArrayList() {
        elements = new Object[DEFAULT_INT_CAPACITY]; //начальный размер
        size = 0;
    }

    public int size() {
        return size;
    }

    // добавление элемента
    public void add(T element) {
        // Если массив заполнен, увеличиваем его размер
        if (size == elements.length) {
            resize();
        }
        // Добавляем элемент в конец массива и увеличиваем размер списка
        elements[size++] = element;
    }

    // получение элемента
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // Проверка на выход за границы списка
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    public void mergeSort(Comparator<T> comparator) {
        if (size > 1) {
            Object[] temp = new Object[size];
            mergeSorting(0, size - 1, temp, comparator);
        }
    }

    //увеличения размера массива
    private void resize() {
        // Создаем новый массив в два раза больше текущего
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void mergeSorting(int left, int right, Object[] temp, Comparator<T> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSorting(left, mid, temp, comparator); // Сортировка левой части
            mergeSorting(mid + 1, right, temp, comparator); // Сортировка правой части
            merge(left, mid, right, temp, comparator); // Слияние двух частей
        }
    }


    @SuppressWarnings("unchecked")
    private void merge(int left, int mid, int right, Object[] temp, Comparator<T> comparator) {
        int i = left; // Индекс для левой части
        int j = mid + 1; // Индекс для правой части
        int k = 0; // Индекс для временного массива

        // Слияние двух частей
        while (i <= mid && j <= right) {
            if (comparator.compare((T) elements[i], (T) elements[j]) <= 0) {
                temp[k++] = elements[i++];
            } else {
                temp[k++] = elements[j++];
            }
        }

        // Добавляем оставшиеся элементы из левой части
        while (i <= mid) {
            temp[k++] = elements[i++];
        }

        // Добавляем оставшиеся элементы из правой части
        while (j <= right) {
            temp[k++] = elements[j++];
        }

        // Копируем отсортированные элементы обратно в исходный массив
        System.arraycopy(temp, 0, elements, left, k);
    }
}


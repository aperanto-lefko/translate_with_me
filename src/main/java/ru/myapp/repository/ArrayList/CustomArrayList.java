package ru.myapp.repository.ArrayList;

import java.util.Arrays;

public class CustomArrayList<T> {
    private Object[] elements; //внутренний массив для хранения элементов
    private int size; // кол-во элементов в списке

    public CustomArrayList() {
        elements = new Object[10]; //начальный размер
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
    public T get(int index) {
        // Проверка на выход за границы списка
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    public void mergeSort() {
        // Вызываем вспомогательный метод для сортировки
        elements = mergeSorting(elements, 0, size - 1);
    }

    //увеличения размера массива
    private void resize() {
        // Создаем новый массив в два раза больше текущего
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private Object[] mergeSorting(Object[] arr, int left, int right) {
        // Если есть более одного элемента, продолжаем сортировку
        if (left < right) {
            int mid = (left + right) / 2; // Находим середину массива
            // Рекурсивно сортируем левую и правую части
            mergeSorting(arr, left, mid);
            mergeSorting(arr, mid + 1, right);
            // Объединяем отсортированные части
            merge(arr, left, mid, right);
        }
        return arr;
    }

    private void merge(Object[] arr, int left, int mid, int right) {
        // Временный массив для хранения результата слияния
        Object[] temp = new Object[right - left + 1];
        int i = left; // Индекс для левой части
        int j = mid + 1; // Индекс для правой части
        int k = 0; // Индекс для временного массива

        // Слияние двух частей
        while (i <= mid && j <= right) {
            if (((Comparable<T>) arr[i]).compareTo((T) arr[j]) <= 0) {
                temp[k++] = arr[i++]; // Элемент из левой части меньше
            } else {
                temp[k++] = arr[j++]; // Элемент из правой части меньше
            }
        }

        // Добавляем оставшиеся элементы из левой части
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Добавляем оставшиеся элементы из правой части
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Копируем отсортированные элементы обратно в исходный массив
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}


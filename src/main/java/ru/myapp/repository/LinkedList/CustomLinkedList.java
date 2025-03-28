package ru.myapp.repository.LinkedList;

import java.util.Comparator;

public class CustomLinkedList<T> {
    // Голова списка (первый элемент)
    private Node<T> head;
    // Текущий размер списка
    private int size;

    // класс Node для представления узла списка
    private static class Node<T> {
        T data; // Данные узла
        Node<T> next; // Ссылка на следующий узел

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public CustomLinkedList() {
        head = null; // Начальное состояние — список пуст
        size = 0; // Начальный размер — 0
    }


    public void add(T value) {
        // Если список пуст, создаем новый узел и делаем его головой
        if (head == null) {
            head = new Node<>(value);
        } else {
            // Иначе идем до последнего узла и добавляем новый узел
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(value);
        }
        size++; // Увеличиваем размер списка
    }

    public T get(int index) {
        // Проверка на выход за границы списка
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Идем по списку до нужного индекса
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // Возвращаем данные узла
        return current.data;
    }

    // Метод для получения текущего размера списка
    public int size() {
        return size;
    }

    public void quickSort(Comparator<T> comparator) {
        head = quickSorting(head, comparator);
    }

    // метод для быстрой сортировки
    private Node<T> quickSorting(Node<T> node, Comparator<T> comparator) {
        if (node == null || node.next == null) {
            return node;
        }

        Node<T> pivot = node; // Опорный элемент
        Node<T> lessHead = new Node<>(null); // Узлы, меньшие pivot
        Node<T> greaterHead = new Node<>(null); // Узлы, большие pivot
        Node<T> less = lessHead;
        Node<T> greater = greaterHead;
        Node<T> current = node.next;

        // Разделение списка на две части
        while (current != null) {
            if (comparator.compare(current.data, pivot.data) <= 0) {
                less.next = current;
                less = less.next;
            } else {
                greater.next = current;
                greater = greater.next;
            }
            current = current.next;
        }

        // Завершаем списки
        less.next = null;
        greater.next = null;

        // Рекурсивно сортируем обе части
        Node<T> sortedLess = quickSorting(lessHead.next, comparator);
        Node<T> sortedGreater = quickSorting(greaterHead.next, comparator);

        // Если левая часть пуста, pivot становится началом списка
        if (sortedLess == null) {
            pivot.next = sortedGreater;
            return pivot;
        }

        // Иначе находим конец левой части и присоединяем pivot и правую часть
        Node<T> temp = sortedLess;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pivot;
        pivot.next = sortedGreater;

        // Возвращаем начало отсортированного списка
        return sortedLess;
    }
}


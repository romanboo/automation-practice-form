package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionExamples {

    public static void main(String[] args) {
        System.out.println("=== ArrayListExample ===");
        ArrayListExample arrayListExample = new ArrayListExample();
        arrayListExample.addElement("Java");
        arrayListExample.addElement("Python");
        arrayListExample.containsElement("Java");
        arrayListExample.removeElement("Python");
        arrayListExample.containsElement("Python");

        System.out.println("\n=== HashSetExample ===");
        HashSetExample hashSetExample = new HashSetExample();
        hashSetExample.addElement(10);
        hashSetExample.addElement(20);
        hashSetExample.containsElement(10);
        hashSetExample.removeElement(20);
        hashSetExample.containsElement(20);

        System.out.println("\n=== HashMapExample ===");
        HashMapExample hashMapExample = new HashMapExample();
        hashMapExample.addElement("One", 1);
        hashMapExample.addElement("Two", 2);
        hashMapExample.containsKey("One");
        hashMapExample.removeElement("Two");
        hashMapExample.containsKey("Two");

        System.out.println("\n=== LinkedListExample ===");
        LinkedListExample linkedListExample = new LinkedListExample();
        linkedListExample.addElement(3.14);
        linkedListExample.addElement(2.71);
        linkedListExample.containsElement(3.14);
        linkedListExample.removeElement(2.71);
        linkedListExample.containsElement(2.71);
    }

    // Класс 1: ArrayList с for-loop
    static class ArrayListExample {
        private List<String> list = new ArrayList<>();

        // Добавление элемента
        public void addElement(String element) {
            list.add(element);
            System.out.println("Добавлен элемент: " + element);
        }

        // Удаление элемента
        public void removeElement(String element) {
            for (int i = 0; i < list.size(); i++) { // Используем for-loop
                if (list.get(i).equals(element)) {
                    String removed = list.remove(i);
                    System.out.println("Удален элемент: " + removed);
                    return;
                }
            }
            System.out.println("Элемент не найден: " + element);
        }

        // Поиск элемента
        public boolean containsElement(String element) {
            for (int i = 0; i < list.size(); i++) { // Используем for-loop
                if (list.get(i).equals(element)) {
                    System.out.println("Элемент найден: " + element);
                    return true;
                }
            }
            System.out.println("Элемент не найден: " + element);
            return false;
        }
    }

    // Класс 2: HashSet с for-each loop
    static class HashSetExample {
        private Set<Integer> set = new HashSet<>();

        // Добавление элемента
        public void addElement(int element) {
            set.add(element);
            System.out.println("Добавлен элемент: " + element);
        }

        // Удаление элемента
        public void removeElement(int element) {
            for (int num : set) { // Используем for-each loop
                if (num == element) {
                    set.remove(num);
                    System.out.println("Удален элемент: " + element);
                    return;
                }
            }
            System.out.println("Элемент не найден: " + element);
        }

        // Поиск элемента
        public boolean containsElement(int element) {
            for (int num : set) { // Используем for-each loop
                if (num == element) {
                    System.out.println("Элемент найден: " + element);
                    return true;
                }
            }
            System.out.println("Элемент не найден: " + element);
            return false;
        }
    }

    // Класс 3: HashMap с while loop и Iterator
    static class HashMapExample {
        private Map<String, Integer> map = new HashMap<>();

        // Добавление элемента
        public void addElement(String key, int value) {
            map.put(key, value);
            System.out.println("Добавлена пара: " + key + " -> " + value);
        }

        // Удаление элемента
        public void removeElement(String key) {
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) { // Используем while loop с итератором
                Map.Entry<String, Integer> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    iterator.remove();
                    System.out.println("Удалена пара с ключом: " + key);
                    return;
                }
            }
            System.out.println("Ключ не найден: " + key);
        }

        // Поиск элемента
        public boolean containsKey(String key) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) { // Используем while loop с итератором
                if (iterator.next().equals(key)) {
                    System.out.println("Ключ найден: " + key);
                    return true;
                }
            }
            System.out.println("Ключ не найден: " + key);
            return false;
        }
    }

    // Класс 4: LinkedList с do-while loop
    static class LinkedListExample {
        private List<Double> list = new ArrayList<>(); // В Java нет отдельного LinkedList в базовых коллекциях

        // Добавление элемента
        public void addElement(double element) {
            list.add(element);
            System.out.println("Добавлен элемент: " + element);
        }

        // Удаление элемента
        public void removeElement(double element) {
            if (list.isEmpty()) {
                System.out.println("Список пуст");
                return;
            }

            int i = 0;
            do { // Используем do-while loop
                if (i < list.size() && list.get(i) == element) {
                    double removed = list.remove(i);
                    System.out.println("Удален элемент: " + removed);
                    return;
                }
                i++;
            } while (i < list.size());

            System.out.println("Элемент не найден: " + element);
        }

        // Поиск элемента
        public boolean containsElement(double element) {
            if (list.isEmpty()) {
                System.out.println("Список пуст");
                return false;
            }

            int i = 0;
            do { // Используем do-while loop
                if (i < list.size() && list.get(i) == element) {
                    System.out.println("Элемент найден: " + element);
                    return true;
                }
                i++;
            } while (i < list.size());

            System.out.println("Элемент не найден: " + element);
            return false;
        }
    }


}
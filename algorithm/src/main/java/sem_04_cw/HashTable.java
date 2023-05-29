package sem_04_cw;

//  Задание 1 (тайминг 10 минут)
//  Начинаем реализацию хэш-таблицы с подготовки структуры и
//  необходимых классов.
//        Давайте напишем реализацию односвязного списка, в котором мы
//        и будем хранить пары ключ-значение.
//        Стоит обратить внимание, что можно использовать как дженерики,
//        для обобщения возможных типов ключей и значений, так и
//        заранее определить для себя конкретные типы, которые будут
//        использоваться в качестве ключа и значения. Оба подхода
//        допустимы для реализации.

//  Добавляем массив связных списков с фиксированным размером (массив
//        бакетов), либо передаваемым в конструкторе.
//        Хэш-таблица оперирует индексами, потому массив будет идеальным
//        вариантов для представления бакетов.
//        Также реализуем метод вычисления индекса на основании хэш-кода
//        ключа.

//Необходимо реализовать методы добавления элементов в связный
//        список, если там еще нет пары с аналогичным ключом и удаления
//        элемента с аналогичным ключом из списка.
//
//        Все значения ключей в хэш-таблице уникальны, а значит и в каждом из
//        связных список это правило будет также выполняться.

//Добавляем информацию о размере хэш-таблицы, а также алгоритм
//        увеличения количества бакетов при достижении количества элементов до
//        определенного размера относительно количества бакетов (load factor).
//
//        Чтобы хэш-таблица сохраняла сложность поиска близкой к O(1), нам
//        необходимо контролировать количество бакетов, чтобы в них не
//        скапливалось слишком много элементов, которые способны увеличить
//        длительность операции поиска и добавления.
//
//        В Java load factor для хэш-таблицы – 0.75, что значит, что при достижении
//        количества значений 75% от общего количества бакетов – это количество
//        необходимо увеличить. Это позволяет минимизировать шансы, что в
//        бакетах будет больше 1-2 значений, а значит сохранит скорость поиска на
//        уровне сложности O(1).

public class HashTable<K, V> {
    private static final int init_basket_count = 16;

    private static final double loadFactor = 0.75;

    private int size = 0;

    private void recalculate() {
        Basket[] oldBaskerArray = basketArray;
        basketArray = (Basket[]) new Object[oldBaskerArray.length * 2];
        for (int i = 0; i < oldBaskerArray.length; i++) {
            Basket basket = oldBaskerArray[i];
            Basket.Node newNode = basket.head;
            while (newNode != null) {
                put(newNode.value.key, newNode.value.value);
                newNode = newNode.next;
            }
            oldBaskerArray[i] = null;
        }
    }

    private Basket[] basketArray;

    public HashTable() {
        this(init_basket_count);
    }

    public HashTable(int initSize) {
        basketArray = (Basket[]) new Object[initSize];
    }

    private class Entity {
        private K key;

        private V value;
    }

    private class Basket {
        private Node head;

        private class Node {
            private Entity value;
            private Node next;
        }

        public V getBasket(K key) {
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.value.key.equals(key)) {
                    return currentNode.value.value;
                }
                currentNode = currentNode.next;
            }
            return null;
        }

        public boolean put(Entity entity) {
            Node node = new Node();
            node.value = entity;

            Node currentNode = head;
            if (currentNode != null) {
                while (true) {
                    if (currentNode.value.key.equals(entity.key)) {
                        return false;
                    }
                    if (currentNode.next == null) {
                        currentNode.next = node;
                        return true;
                    }
                    currentNode = currentNode.next;
                }
            }
            head = node;
            return true;
        }

        public boolean remove(K key) {
            if (head != null) {
                if (head.value.key.equals(key)) {
                    head = head.next;
                    return true;
                } else {
                    Node currentNode = head;
                    while (currentNode.next != null) {
                        if (currentNode.next.value.key.equals(key)) {
                            currentNode.next = currentNode.next.next;
                            return true;
                        }
                        currentNode = currentNode.next;
                    }
                }
            }
            return false;
        }
    }

    private int calculateBasketIndex(K key) {
        return key.hashCode() % basketArray.length;
    }

    public V getBasket(K key) {
        int basketIndex = calculateBasketIndex(key);
        Basket basket = basketArray[basketIndex];
        if (basket != null) {
            return basket.getBasket(key);
        }
        return null;
    }

    public boolean put(K key, V value) {
        if (basketArray.length * loadFactor < size) {
            recalculate();
        }
        int basketIndex = calculateBasketIndex(key);
        Basket basket = basketArray[basketIndex];
        if (basket == null) {
            basket = new Basket();
            basketArray[basketIndex] = basket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        boolean added = basket.put(entity);
        if (added) {
            size++;
        }
        return added;
    }

    public boolean remove(K key) {
        int basketIndex = calculateBasketIndex(key);
        Basket basket = basketArray[basketIndex];
        boolean removed = basket.remove(key);
        if (removed) {
            size--;
        }
        return removed;
    }
}

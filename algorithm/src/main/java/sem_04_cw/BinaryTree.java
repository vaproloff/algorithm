package sem_04_cw;

//  Реализуем структуру бинарного дерева.
//
//  Для бинарного дерева характерно наличии двух потомков, где левый
//  меньше родителя, а правый – больше.
//
//  Для реализации можно использовать как и простое числовое дерево, так
//  и обобщенный тип. Учитывая, что мы строим именно бинарное дерево, то
//  при использовании обобщенных типов убедитесь, что значение
//  поддерживает сравнение (интерфейс Comparable)

public class BinaryTree <V extends Comparable<V>> {
    private Node root;

    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;
    }

    private boolean find(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        if (value > node.value) {
            return find(node.rightChild, value);
        } else {
            return find(node.leftChild, value);
        }
    }
}

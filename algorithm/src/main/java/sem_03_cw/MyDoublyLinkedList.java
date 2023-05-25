package sem_03_cw;

public class MyDoublyLinkedList {
    private Node head;
    private Node tail;

    private class Node {
        private int value;
        private Node next;
        private Node previous;
    }

    public void addFirst(int value) {
        Node firstNode = new Node();
        firstNode.value = value;
        if (head != null) {
            head.previous = firstNode;
            firstNode.next = head;
        } else {
            tail = firstNode;
        }
        head = firstNode;
    }

    public void removeFirst() {
        if (head != null && head.next != null) {
            head = head.next;
            head.previous = null;
        }
        else {
            head = null;
            tail = null;
        }
    }

    public boolean ifFound(int value) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void addLast(int value) {
        if (head == null) {
            addFirst(value);
        } else {
            Node lastNode = new Node();
            lastNode.value = value;
            tail.next = lastNode;
            lastNode.previous = tail;
            tail = lastNode;
        }
    }

    public void removeLast() {
        if (head != null && head.next != null) {
            tail = tail.previous;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }
    }

    public void bubbleSort() {
        boolean needSort = true;
        while (needSort) {
            needSort = false;
            Node currentNode = head;
            while (currentNode != null && currentNode.next != null) {
                if (currentNode.value > currentNode.next.value) {
                    int temp = currentNode.value;
                    currentNode.value = currentNode.next.value;
                    currentNode.next.value = temp;
                    needSort = true;
                }
                currentNode = currentNode.next;
            }
        }
    }
}

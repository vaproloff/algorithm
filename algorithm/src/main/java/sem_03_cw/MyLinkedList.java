package sem_03_cw;

public class MyLinkedList {
    private Node head;

    private class Node {
        private int value;
        private Node next;
    }

    public void addFirst(int value) {
        Node firstNode = new Node();
        firstNode.value = value;
        if (head != null) {
            firstNode.next = head;
        }
        head = firstNode;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
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
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = lastNode;
        }
    }

    public void removeLast() {
        if (head != null) {
            Node currentNode = head;
            while (currentNode.next != null) {
                if (currentNode.next.next == null) {
                    currentNode.next = null;
                    return;
                }
                currentNode = currentNode.next;
            }
            head = null;
        }
    }
}

package sem_04_hw;

public class BlackRedTree {
    private Node root;

    public BlackRedTree() {
        this.root = null;
    }

    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;
        private boolean ifRed;

        public Node(int value) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
            this.ifRed = true;
        }
    }

    private Node leftRotate(Node node) {
        Node leftChild = node.leftChild;
        Node middleChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = middleChild;
        leftChild.ifRed = node.ifRed;
        node.ifRed = true;
        return leftChild;
    }

    private Node rightRotate(Node node) {
        Node rightChild = node.rightChild;
        Node middleChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = middleChild;
        rightChild.ifRed = node.ifRed;
        node.ifRed = true;
        return rightChild;
    }

    private void colorSwap(Node node) {
        node.leftChild.ifRed = false;
        node.rightChild.ifRed = false;
        node.ifRed = true;
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean ifBalanced;
        do {
            ifBalanced = true;
            if (result.rightChild != null && result.rightChild.ifRed && (result.leftChild == null || !result.leftChild.ifRed)) {
                ifBalanced = false;
                result = rightRotate(result);
            }
            if (result.leftChild != null && result.leftChild.ifRed && result.leftChild.leftChild != null && result.leftChild.leftChild.ifRed) {
                ifBalanced = false;
                result = leftRotate(result);
            }
            if (result.leftChild != null && result.leftChild.ifRed && result.rightChild != null && result.rightChild.ifRed) {
                ifBalanced = false;
                colorSwap(result);
            }
        } while (!ifBalanced);
        return result;
    }

    private boolean addNodeHelper(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (value < node.value) {
                if (node.leftChild != null) {
                    boolean ifAdded = addNodeHelper(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return ifAdded;
                } else {
                    node.leftChild = new Node(value);
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean ifAdded = addNodeHelper(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return ifAdded;
                } else {
                    node.rightChild = new Node(value);
                    return true;
                }
            }
        }
    }

    public boolean addNode(int value) {
        if (root != null) {
            boolean ifAdded = addNodeHelper(root, value);
            root = rebalance(root);
            root.ifRed = false;
            return ifAdded;
        } else {
            root = new Node(value);
            root.ifRed = false;
            return true;
        }
    }

    private void printTreeHelper(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = node.ifRed ? "RED" : "BLACK";
            System.out.println(node.value + "(" + sColor + ")");
            printTreeHelper(node.leftChild, indent, false);
            printTreeHelper(node.rightChild, indent, true);
        }
    }

    public void printTree() {
        printTreeHelper(this.root, "", true);
    }
}

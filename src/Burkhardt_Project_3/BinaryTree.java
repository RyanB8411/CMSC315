package Burkhardt_Project_3;
import java.util.List;
import java.util.ArrayList;
public class BinaryTree {
    private Node root;

    public static class Node {
        private final int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    public BinaryTree(String preorder) {
        if (preorder == null || preorder.isEmpty()) {
            throw new IllegalArgumentException("Invalid preorder representation");
        }
    
        List<Node> nodes = new ArrayList<>();
        for (String s : preorder.replaceAll("[\\(\\)]", "").split("\\s+")) {
            if (s.equals("*")) {
                nodes.add(null);
            } else {
                nodes.add(new Node(Integer.parseInt(s), null, null));
            }
        }
    
        root = buildTree(nodes, 0, nodes.size() - 1);
    }
    
    private Node buildTree(List<Node> nodes, int left, int right) {
        if (left > right) {
            return null;
        }
    
        Node node = nodes.get(left);
        if (node == null) {
            return null;
        }
    
        int rootIndex = left;
        while (rootIndex <= right && nodes.get(rootIndex + 1) != null && nodes.get(rootIndex + 1).value > node.value) {
            rootIndex++;
        }
    
        node.left = buildTree(nodes, left, rootIndex - 1);
        if (rootIndex < right) {
            Node rightChild = nodes.get(rootIndex + 1);
            if (rightChild != null) {
                node.right = rightChild;
            }
        }
    
        return node;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.value < min || node.value > max) {
            return false;
        }

        return isBinarySearchTree(node.left, min, node.value) && isBinarySearchTree(node.right, node.value, max);
    }

    public boolean isBalanced(){
    return isBalanced(root).isBalanced;
    }

    private static class BalanceResult {
        public boolean isBalanced;
        public int height;

        public BalanceResult(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private BalanceResult isBalanced(Node node) {
        if (node == null) {
            return new BalanceResult(true, -1);
        }

        BalanceResult leftResult = isBalanced(node.left);
        BalanceResult rightResult = isBalanced(node.right);

        boolean isBalanced = leftResult.isBalanced && rightResult.isBalanced && Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;

        return new BalanceResult(isBalanced, height);
    }

    public List<Integer> toList() {
        List<Integer> values = new ArrayList<>();
        toList(root, values);
        return values;
    }

    private void toList(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }

        values.add(node.value);
        toList(node.left, values);
        toList(node.right, values);
    }
}
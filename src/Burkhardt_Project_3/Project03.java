package Burkhardt_Project_3;

import java.util.Scanner;

public class Project03 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter a binary tree: ");
		String expression = in.nextLine();
//		String expression = "(53(28(11**)(41**))(83(67**)*))";
//		String expression = "(55(32(41**)*)(51**))";
		BinaryTree tree = parseExpression(expression);
		displayTree(tree);
		int treeHeight = getTreeHeight(tree);
		System.out.println("\nThe height of the binary tree is " + treeHeight);
	}

	private static BinaryTree parseExpression(String expression) {
		int data = 0;
		int binaryTreeCount = 0;
		BinaryTree leftChild = null;
		BinaryTree rightChild = null;
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == '(' || ch == '*') {
				if (ch == '(') {
					if (binaryTreeCount == 1) {
						String leftExpression = extractExpression(expression, i);
						leftChild = parseExpression(leftExpression);
						i += leftExpression.length() - 1;
					} else if (binaryTreeCount == 2) {
						String rightExpression = extractExpression(expression, i);
						rightChild = parseExpression(rightExpression);
						i += rightExpression.length() - 1;
					}
				}

				binaryTreeCount++;
			}

			if (Character.isDigit(ch)) {
				data *= 10;
				data += ch - '0';
			}
		}

		return new BinaryTree(data, leftChild, rightChild);
	}

	private static String extractExpression(String expression, int start) {
		int parenthesesCount = 0;
		int i = start;
		for (; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (ch == '(') {
				parenthesesCount++;
			} else if (ch == ')') {
				parenthesesCount--;
			}

			if (parenthesesCount == 0)
				break;
		}

		return expression.substring(start, i + 1);
	}
	
	private static void displayTree(BinaryTree tree) {
		displayTree(tree, 0);
	}
	
	private static void displayTree(BinaryTree tree, int level) {
		if (tree == null) {
			return;
		}
		
		for (int i = 0; i < level; i++) {
			System.out.print("  ");
		}
		
		System.out.println(tree.getData());
		displayTree(tree.getLeft(), level + 1);
		displayTree(tree.getRight(), level + 1);
	}
	
	public static int getTreeHeight(BinaryTree tree) {
		return getTreeHeight(tree, 1);
	}
	
	private static int getTreeHeight(BinaryTree tree, int level) {
		if (tree == null) {
			return level;
		}

		return Math.max(
				getTreeHeight(tree.getLeft(), level + 1),
				getTreeHeight(tree.getRight(), level + 1)
				);
	}
}
class BinaryTree {
    private int data;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int data, BinaryTree left, BinaryTree right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public BinaryTree getLeft() {
        return left;
	}
	public BinaryTree getRight() {
        return right;
	}
}
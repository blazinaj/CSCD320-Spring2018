import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		private final int RED = 0;
		private final int BLACK = 1;

		private class Node {
			int key = -1, color = BLACK;
			Node left = nil, right = nil, parent = nil;
			
			Node(int key){
				this.key = key;
			}
		}
		
		private final Node nil = new Node(-1);
		private Node root = nil;
		
		private void insert(Node newNode) {
			Node currentNode = root;
			// root is nil
			if (root == nil) {
				root = newNode;
				newNode.color = BLACK;
				newNode.parent = nil;
			}
			// root (current node) is not nil
			else {
				newNode.color = RED;
				// loop through rest of tree
				while (true) {
					// new node key is less than current node key
					if (newNode.key < currentNode.key) {
						// current node left child is nil
						if (currentNode.left == nil) {
							// set current node left child to new node
							currentNode.left = newNode;
							// set new node parent to current node
							newNode.parent = currentNode;
							break;
						}
						// traverse through left subtree now
						else {
							currentNode = currentNode.left;
						}
					}
					// new node key is greater than or equal to current node key
					else if (newNode.key >= currentNode.key) {
						if (currentNode.right == nil) {
							currentNode.right = newNode;
							newNode.parent = currentNode;
							break;
						}
						// traverse through subright tree now
						else {
							currentNode = currentNode.right;
						}
					}
				} // end while loop
				fixTree(newNode);
			}
		}
	}

}

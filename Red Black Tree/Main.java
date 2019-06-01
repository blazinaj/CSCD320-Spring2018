import java.util.Scanner;

public class Main {

	private final int RED = 0;
	private final int BLACK = 1;

	public static void main(String[] args) {
		Main tree = new Main();
		
//		tree.insert(6, 'F');
//		tree.insert(4, 'B');
//		tree.insert(7, 'H');
//		tree.insert(2, 'A');
//		tree.insert(5, 'D');
//		tree.insert(8, 'K');
//		
//		tree.inOrder();
//		tree.postOrder();
//		tree.preOrder();
		Scanner scan = new Scanner(System.in);
		
		String input;
		int key;
		char value;
		
		do {
			input = scan.next();
			switch (input) {
			case "INSERT":
	         Scanner innerScan = new Scanner(scan.nextLine());
				while ((innerScan.hasNext())) {
					key = Integer.parseInt(innerScan.next());
					value = innerScan.next().charAt(0);
					tree.insert(key,  value);
				}
	            innerScan.close();
	            break;
 			case "POSTORDER":
				tree.postOrder();
				break;
         case "POSTORDERBLACK":
				tree.postOrderBlack();
				break;
         case "POSTORDERRED":
				tree.postOrderRed();
				break;
			case "INORDER":
 				tree.inOrder();
 				break;
         case "INORDERBLACK":
            tree.inOrderBlack();
            break;
         case "INORDERRED":
            tree.inOrderRed();
            break;
 			case "PREORDER":
 				tree.preOrder();
 				break;
         case "PREORDERBLACK":
 				tree.preOrderBlack();
 				break;
         case "PREORDERRED":
 				tree.preOrderRed();
 				break;
 			case "DELETE":
 				Scanner deleteScanner = new Scanner(scan.nextLine());
 				while ((deleteScanner.hasNext())) {
 					key = Integer.parseInt(deleteScanner.next());
 					tree.deleteNode(key);
 				}
 				deleteScanner.close();
 				break;
 		 	}
		} while (!input.equals("EXIT"));
		scan.close();
   }
   
	private class Node {
		int key = -1, color = BLACK;
      char value;
		Node left = nil, right = nil, parent = nil;
		
		Node(int key, char letter){
			this.key = key;
         this.value = letter;
		}
      
      Node(int key){
         this.key = key;
      }
	}
		
	private final Node nil = new Node(-1);
	private Node root = nil;
	
	private void insert(int key, char value) {
      Node newNode = new Node(key, value);
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
			balanceTree(newNode);
		}
	}

    private void balanceTree(Node currentNode) {
        while (currentNode.parent.color == RED) {
            Node uncle = nil;
            if (currentNode.parent == currentNode.parent.parent.left) {
                uncle = currentNode.parent.parent.right;

                if (uncle != nil && uncle.color == RED) {
                    currentNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    currentNode.parent.parent.color = RED;
                    currentNode = currentNode.parent.parent;
                    continue;
                } 
                if (currentNode == currentNode.parent.right) {
                    currentNode = currentNode.parent;
                    rotateLeft(currentNode);
                } 
                currentNode.parent.color = BLACK;
                currentNode.parent.parent.color = RED;
                rotateRight(currentNode.parent.parent);
            } else {
                uncle = currentNode.parent.parent.left;
                 if (uncle != nil && uncle.color == RED) {
                    currentNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    currentNode.parent.parent.color = RED;
                    currentNode = currentNode.parent.parent;
                    continue;
                }
                if (currentNode == currentNode.parent.left) {
                    currentNode = currentNode.parent;
                    rotateRight(currentNode);
                }
                currentNode.parent.color = BLACK;
                currentNode.parent.parent.color = RED;
                rotateLeft(currentNode.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node currentNode) {
        if (currentNode.parent != nil) {
            if (currentNode == currentNode.parent.left) {
                currentNode.parent.left = currentNode.right;
            } else {
                currentNode.parent.right = currentNode.right;
            }
            currentNode.right.parent = currentNode.parent;
            currentNode.parent = currentNode.right;
            if (currentNode.right.left != nil) {
                currentNode.right.left.parent = currentNode;
            }
            currentNode.right = currentNode.right.left;
            currentNode.parent.left = currentNode;
        } 
        else {
            Node rightNode = currentNode.right;
            root.right = rightNode.left;
            rightNode.left.parent = root;
            root.parent = rightNode;
            rightNode.left = root;
            rightNode.parent = nil;
            root = rightNode;
        }
    }

    void rotateRight(Node currentNode) {
        if (currentNode.parent != nil) {
            if (currentNode == currentNode.parent.left) {
                currentNode.parent.left = currentNode.left;
            } 
            else {
                currentNode.parent.right = currentNode.left;
            }

            currentNode.left.parent = currentNode.parent;
            currentNode.parent = currentNode.left;
            if (currentNode.left.right != nil) {
                currentNode.left.right.parent = currentNode;
            }
            currentNode.left = currentNode.left.right;
            currentNode.parent.right = currentNode;
        } 
        else {
            Node leftNode = root.left;
            root.left = root.left.right;
            leftNode.right.parent = root;
            root.parent = leftNode;
            leftNode.right = root;
            leftNode.parent = nil;
            root = leftNode;
        }
    }
    
    //Delete
    
    void transplant(Node target, Node with){ 
          if(target.parent == nil){
              root = with;
          }
          else if(target == target.parent.left){
              target.parent.left = with;
          }
          else {
              target.parent.right = with;
          }
          with.parent = target.parent;
    }
    
    private Node findNode(Node findNode, Node node) {
        if (root == nil) {
            return null;
        }
   
        if (findNode.key < node.key) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }
    
    void deleteNode(int key) {
      root = deleteNodeRecursively(root, key);
    }
    
    Node deleteNodeRecursively(Node root, int key){
      // tree is empty
		if (root == nil)
			return root;
		
		// recurse down the tree
		if (key < root.key) {
			root.left = deleteNodeRecursively(root.left, key);
		}
		else if (key > root.key) {
			root.right = deleteNodeRecursively(root.right, key);
		}
		// found the key
		else {
			if (delete(root) == true)
            root = nil;
		}
		return root;
    }
    
    boolean delete(Node z){
        if((z = findNode(z, root))== null){
         return false;
        }
        Node x;
        Node y = z;
        int ycolor = y.color;
        
        if(z.left == nil){
            x = z.right;  
            transplant(z, z.right);  
        }
        else if(z.right == nil){
            x = z.left;
            transplant(z, z.left); 
        }
        else{
            y = treeMinimum(z.right);
            ycolor = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color; 
        }
        if(ycolor == BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.color == BLACK){ 
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK; 
    }
    
    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
    	// In order Traversal
	void inOrder() {
		inOrderRecursively(root);
		System.out.println();
	}
   
   void inOrderBlack() {
		inOrderBlackRecursively(root);
		System.out.println();
	}
   
   void inOrderBlackRecursively(Node root) {
		if (root != null && root != nil) {
			inOrderBlackRecursively(root.left);
         if (root.color == BLACK){
			   System.out.print(root.value);
         }
			inOrderBlackRecursively(root.right);
		}
	}
	
	// Recursive helper for In Order Traversal.
	void inOrderRecursively(Node root) {
		if (root != null && root != nil) {
			inOrderRecursively(root.left);
			System.out.print(root.value);
			inOrderRecursively(root.right);
		}
	}
	
	// Pre Order Traversal
	void preOrder() {
		preOrderRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void preOrderRecursively(Node root) {
		if (root != null && root != nil) {
			System.out.print(root.value);
			preOrderRecursively(root.left);
			preOrderRecursively(root.right);
		}
	}
   
   void preOrderBlack() {
		preOrderBlackRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void preOrderBlackRecursively(Node root) {
		if (root != null && root != nil) {
         if (root.color == BLACK){
            System.out.print(root.value);
         }
			preOrderBlackRecursively(root.left);
			preOrderBlackRecursively(root.right);
		}
	}
	
	// Pre Order Traversal
	void postOrder() {
		postOrderRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void postOrderRecursively(Node root) {
		if (root != null && root != nil) {
			postOrderRecursively(root.left);
			postOrderRecursively(root.right);
			System.out.print(root.value);
		}
	}
   
   void postOrderBlack() {
		postOrderBlackRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void postOrderBlackRecursively(Node root) {
		if (root != null && root != nil) {
			postOrderBlackRecursively(root.left);
			postOrderBlackRecursively(root.right);
         if (root.color == BLACK){
            System.out.print(root.value);
         }
		}
	}
   
   void postOrderRed() {
		postOrderRedRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void postOrderRedRecursively(Node root) {
		if (root != null && root != nil) {
			postOrderRedRecursively(root.left);
			postOrderRedRecursively(root.right);
         if (root.color == RED){
            System.out.print(root.value);
         }
		}
	}
   
   void preOrderRed() {
		preOrderRedRecursively(root);
		System.out.println();
	}
	
	// Recursive helper for Pre Order Traversal
	void preOrderRedRecursively(Node root) {
		if (root != null && root != nil) {
         if (root.color == RED){
            System.out.print(root.value);
         }
			preOrderRedRecursively(root.left);
			preOrderRedRecursively(root.right);
		}
	}
   
   void inOrderRed() {
      inOrderRedRecursively(root);
      System.out.println();
   }
   
   void inOrderRedRecursively(Node root) {
		if (root != null && root != nil) {
			inOrderRedRecursively(root.left);
         if (root.color == RED){
			   System.out.print(root.value);
         }
			inOrderRedRecursively(root.right);
		}
	}
}

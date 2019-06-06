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

    class RBTNode {
        int key = -1, color = BLACK;
        char value;
        RBTNode left = nil, right = nil, parent = nil;

        RBTNode(int key, char letter){
            this.key = key;
            this.value = letter;
        }

        RBTNode(int key){
            this.key = key;
        }
    }

    private RBTNode nil = new RBTNode(-1);
    private RBTNode root = nil;

    private void insert(int key, char value) {
        RBTNode newNode = new RBTNode(key, value);
        RBTNode currentNode = root;
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
            workOnTreeSingleNode(newNode, TreeOperation.balance);
        }
    }

    enum TreeOperation {
        balance,
        rotateLeft,
        rotateRight,
        transplant,
        find,
        minimum
    }

    private RBTNode workOnTreeSingleNode(RBTNode currentNode, TreeOperation operation) {
        switch (operation) {
            case balance:
                while (currentNode.parent.color == RED) {
                    RBTNode uncle = nil;
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
                            workOnTreeSingleNode(currentNode, TreeOperation.rotateLeft);
                        }
                        currentNode.parent.color = BLACK;
                        currentNode.parent.parent.color = RED;
                        workOnTreeSingleNode(currentNode.parent.parent, TreeOperation.rotateRight);
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
                            workOnTreeSingleNode(currentNode, TreeOperation.rotateRight);
                        }
                        currentNode.parent.color = BLACK;
                        currentNode.parent.parent.color = RED;
                        workOnTreeSingleNode(currentNode.parent.parent, TreeOperation.rotateLeft);
                    }
                }
                root.color = BLACK;
                return null;
            case rotateLeft:
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
                    RBTNode rightNode = currentNode.right;
                    root.right = rightNode.left;
                    rightNode.left.parent = root;
                    root.parent = rightNode;
                    rightNode.left = root;
                    rightNode.parent = nil;
                    root = rightNode;
                }
                return null;
            case rotateRight:
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
                    RBTNode leftNode = root.left;
                    root.left = root.left.right;
                    leftNode.right.parent = root;
                    root.parent = leftNode;
                    leftNode.right = root;
                    leftNode.parent = nil;
                    root = leftNode;
                }
                return null;
            case minimum:
                while(currentNode.left!=nil){
                    currentNode = currentNode.left;
                }
                return currentNode;
            default:
                return null;
        }
    }

    RBTNode workOnTreeTwoNodes(RBTNode nodeOne, RBTNode nodeTwo, TreeOperation operation) {
        switch (operation) {
            case transplant:
                if(nodeOne.parent == nil){
                    root = nodeTwo;
                }
                else if(nodeOne == nodeOne.parent.left){
                    nodeOne.parent.left = nodeTwo;
                }
                else {
                    nodeOne.parent.right = nodeTwo;
                }
                nodeTwo.parent = nodeOne.parent;
                return null;
            case find:
                if (root == nil) {
                    return null;
                }

                if (nodeOne.key < nodeTwo.key) {
                    if (nodeTwo.left != nil) {
                        return workOnTreeTwoNodes(nodeOne, nodeTwo.left, TreeOperation.find);
                    }
                } else if (nodeOne.key > nodeTwo.key) {
                    if (nodeTwo.right != nil) {
                        return workOnTreeTwoNodes(nodeOne, nodeTwo.right, TreeOperation.find);
                    }
                } else if (nodeOne.key == nodeTwo.key) {
                    return nodeTwo;
                }
                return null;
            default:
                return null;
        }
    }

    void deleteNode(int key) {
        root = deleteNodeRecursively(root, key);
    }

    RBTNode deleteNodeRecursively(RBTNode root, int key){
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

    boolean delete(RBTNode nodeToDelete){
        if((nodeToDelete = workOnTreeTwoNodes(nodeToDelete, root, TreeOperation.find))== null){
            return false;
        }
        RBTNode sibling;
        RBTNode currentNode = nodeToDelete;
        int ycolor = currentNode.color;

        if(nodeToDelete.left == nil){
            sibling = nodeToDelete.right;
            workOnTreeTwoNodes(nodeToDelete, nodeToDelete.right, TreeOperation.transplant);
        }
        else if(nodeToDelete.right == nil){
            sibling = nodeToDelete.left;
            workOnTreeTwoNodes(nodeToDelete, nodeToDelete.left, TreeOperation.transplant);
        }
        else{
            currentNode = workOnTreeSingleNode(nodeToDelete.right, TreeOperation.minimum);
            ycolor = currentNode.color;
            sibling = currentNode.right;
            if(currentNode.parent == nodeToDelete)
                sibling.parent = currentNode;
            else{
                workOnTreeTwoNodes(currentNode, currentNode.right, TreeOperation.transplant);
                currentNode.right = nodeToDelete.right;
                currentNode.right.parent = currentNode;
            }
            workOnTreeTwoNodes(nodeToDelete, currentNode, TreeOperation.transplant);
            currentNode.left = nodeToDelete.left;
            currentNode.left.parent = currentNode;
            currentNode.color = nodeToDelete.color;
        }

        if (ycolor == BLACK) {
            RBTNode startingNode;
            startingNode = sibling;
            while (startingNode != root && startingNode.color == BLACK) {
                if (startingNode == startingNode.parent.left) {
                    RBTNode parentRightChild = startingNode.parent.right;
                    if (parentRightChild.color == RED) {
                        parentRightChild.color = BLACK;
                        startingNode.parent.color = RED;
                        workOnTreeSingleNode(startingNode.parent, TreeOperation.rotateLeft);
                        parentRightChild = startingNode.parent.right;
                    }
                    if (parentRightChild.left.color == BLACK && parentRightChild.right.color == BLACK) {
                        parentRightChild.color = RED;
                        startingNode = startingNode.parent;
                        continue;
                    } else if (parentRightChild.right.color == BLACK) {
                        parentRightChild.left.color = BLACK;
                        parentRightChild.color = RED;
                        workOnTreeSingleNode(parentRightChild, TreeOperation.rotateRight);
                        parentRightChild = startingNode.parent.right;
                    }
                    if (parentRightChild.right.color == RED) {
                        parentRightChild.color = startingNode.parent.color;
                        startingNode.parent.color = BLACK;
                        parentRightChild.right.color = BLACK;
                        workOnTreeSingleNode(startingNode.parent, TreeOperation.rotateLeft);
                        startingNode = root;
                    }
                } else {
                    RBTNode parentLeftChild = startingNode.parent.left;
                    if (parentLeftChild.color == RED) {
                        parentLeftChild.color = BLACK;
                        startingNode.parent.color = RED;
                        workOnTreeSingleNode(startingNode.parent, TreeOperation.rotateRight);
                        parentLeftChild = startingNode.parent.left;
                    }
                    if (parentLeftChild.right.color == BLACK && parentLeftChild.left.color == BLACK) {
                        parentLeftChild.color = RED;
                        startingNode = startingNode.parent;
                        continue;
                    } else if (parentLeftChild.left.color == BLACK) {
                        parentLeftChild.right.color = BLACK;
                        parentLeftChild.color = RED;
                        workOnTreeSingleNode(parentLeftChild, TreeOperation.rotateLeft);
                        parentLeftChild = startingNode.parent.left;
                    }
                    if (parentLeftChild.left.color == RED) {
                        parentLeftChild.color = startingNode.parent.color;
                        startingNode.parent.color = BLACK;
                        parentLeftChild.left.color = BLACK;
                        workOnTreeSingleNode(startingNode.parent, TreeOperation.rotateRight);
                        startingNode = root;
                    }
                }
            }
            startingNode.color = BLACK;
        }
        return true;
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

    void inOrderBlackRecursively(RBTNode root) {
        if (root != null && root != nil) {
            inOrderBlackRecursively(root.left);
            if (root.color == BLACK){
                System.out.print(root.value);
            }
            inOrderBlackRecursively(root.right);
        }
    }

    // Recursive helper for In Order Traversal.
    void inOrderRecursively(RBTNode root) {
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
    void preOrderRecursively(RBTNode root) {
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
    void preOrderBlackRecursively(RBTNode root) {
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
    void postOrderRecursively(RBTNode root) {
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
    void postOrderBlackRecursively(RBTNode root) {
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
    void postOrderRedRecursively(RBTNode root) {
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
    void preOrderRedRecursively(RBTNode root) {
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

    void inOrderRedRecursively(RBTNode root) {
        if (root != null && root != nil) {
            inOrderRedRecursively(root.left);
            if (root.color == RED){
                System.out.print(root.value);
            }
            inOrderRedRecursively(root.right);
        }
    }
}

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree tree = new Main().new BinarySearchTree();
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
                        BSTNode newNode = new Main().new BSTNode(key, value);
                        tree.insertNode(newNode);
                    }
                    innerScan.close();
                    break;
                case "POSTORDER":
                    tree.treeTraversal(input);
                    break;
                case "INORDER":
                    tree.treeTraversal(input);
                    break;
                case "PREORDER":
                    tree.treeTraversal(input);
                    break;
                case "DELETE":
                    Scanner deleteScanner = new Scanner(scan.nextLine());
                    while ((deleteScanner.hasNextInt())) {
                        key = deleteScanner.nextInt();
                        tree.removeNodeByKey(key);
                    }
                    deleteScanner.close();
                    break;
            }
        } while (!input.equals("EXIT"));
        scan.close();
    }

    class BSTNode {
        private Integer key;
        private Character value;
        public BSTNode leftChild, rightChild;

        protected BSTNode(Integer key, Character value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return this.key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Character getValue() {
            return this.value;
        }

        public void setValue(Character value) {
            this.value = value;
        }

        public void printValue() {
            System.out.print(this.value);
        }
    }

    class BinarySearchTree {

        private BSTNode rootNode = null;

        protected void insertNode(BSTNode nodeToInsert) {
            rootNode = insertNode(nodeToInsert, rootNode);
        }

        private BSTNode insertNode(BSTNode nodeToInsert, BSTNode rootNode) {
            if (rootNode == null) {
                rootNode = nodeToInsert;
                return rootNode;
            }

            if (nodeToInsert.getKey() < rootNode.getKey())
                rootNode.leftChild = insertNode(nodeToInsert, rootNode.leftChild);

            if (nodeToInsert.getKey() >= rootNode.getKey())
                rootNode.rightChild = insertNode(nodeToInsert, rootNode.rightChild);

            return rootNode;
        }

        protected void removeNodeByKey(Integer keyToRemove) {
            this.rootNode = removeNodeByKey(keyToRemove, this.rootNode);
        }

        private BSTNode removeNodeByKey(Integer keyToRemove, BSTNode rootNode) {
            if (rootNode == null) {
                return rootNode;
            }
            if (rootNode.getKey() > keyToRemove) {
                rootNode.leftChild = removeNodeByKey(keyToRemove, rootNode.leftChild);
            }
            else if (rootNode.getKey() <= keyToRemove) {
                rootNode.rightChild = removeNodeByKey(keyToRemove, rootNode.rightChild);
            }
            if (rootNode.getKey() == keyToRemove) {
                if (rootNode.leftChild == null)
                    return rootNode.rightChild;
                if (rootNode.rightChild == null)
                    return rootNode.leftChild;

                BSTNode minimumNode = findMinimumNode(rootNode.rightChild);

                rootNode.setKey(minimumNode.getKey());
                rootNode.setValue(minimumNode.getValue());
            }
            return rootNode;
        }

        private BSTNode findMinimumNode(BSTNode rootNode) {
            BSTNode smallestSoFar = rootNode;
            while (rootNode.leftChild != null) {
                smallestSoFar = rootNode.leftChild;
                rootNode = rootNode.leftChild;
            }
            return smallestSoFar;
        }

        protected void treeTraversal(String type) {
            switch (type) {
                case "INORDER":
                    inOrderTraversal(this.rootNode);
                    break;
                case "POSTORDER":
                    postOrderTraversal(this.rootNode);
                    break;
                case "PREORDER":
                    preOrderTraversal(this.rootNode);
                    break;
                default:
                    break;
            }
            System.out.println();
        }

        private void inOrderTraversal(BSTNode rootNode) {
            if (rootNode != null) {
                inOrderTraversal(rootNode.leftChild);
                rootNode.printValue();
                inOrderTraversal(rootNode.rightChild);
            }
        }

        private void postOrderTraversal(BSTNode rootNode) {
            if (rootNode != null) {
                postOrderTraversal(rootNode.leftChild);
                postOrderTraversal(rootNode.rightChild);
                rootNode.printValue();
            }
        }

        private void preOrderTraversal(BSTNode rootNode) {
            if (rootNode != null) {
                rootNode.printValue();
                preOrderTraversal(rootNode.leftChild);
                preOrderTraversal(rootNode.rightChild);
            }
        }
    }
}


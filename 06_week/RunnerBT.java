import java.util.Scanner;

public class RunnerBT {

    public static void main(String [] args) {
        System.out.println("Enter the numbers to insert: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        String[] input_string = input.split(",");

        // create an object of class BinaryTree
        // which its variable root is set to null by default
        BinaryTree tree = new BinaryTree();

        for (int i = 0; i < input_string.length; i++) {
            try {
                int number = Integer.parseInt(input_string[i]);
                tree.add(number);
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter only Integers.");
                break;
            }

        }
        scan.close();

        System.out.println("Binary Tree:");
        tree.traverseInOrderBinaryTree(tree.root);
        int result_level = tree.depth(tree.root);
        int final_result;

        if (result_level <= 1) {
            final_result = 0;
        }
        else {
            // get height
            final_result = result_level - 1;
        }

        System.out.println("The resulting binary tree has " + final_result + " level" + (final_result <= 1 ? "." : "s."));
    }
}


class BinaryTree {
    // pointer to either left node or right node depending on value
    Node root;

    // constructor
    BinaryTree() {
        root = null;
    }

    public void add(int number) {
        root = addRecursive(root, number);
    }

    // add node recursively
    private Node addRecursive(Node current, int number) {

        if (current == null) {
            return new Node(number);
        }
        else {
            // e. g. 5 as root and 3 insert -> current.left pointer is equall
            // to addRecursive which will be null in next step -> added
            if (number < current.key) {
                current.left = addRecursive(current.left, number);
            }
            // e. g. 5 as root and 8 insert -> current.right pointer is equall
            // to addRecursive which will be null in next step -> added
            if (number > current.key) {
                current.right = addRecursive(current.right, number);
            }
            // number == current.key since it is same number :)
            else {
                return current;
            }
        }
        return current;
    }

    // get depth of binary tree
    public int depth(Node node)
    {
        int depth_left = 1;
        int depth_right = 1;
        
        if (node == null) {
            return 0;
        }
        else {
            // depth of left subtree
            depth_left = depth_left + depth(node.left);
            // depth of right subtree
            depth_right = depth_right + depth(node.right);
        }
        if ( depth_left > depth_right) {
            return depth_left;
        }
        else {
            return depth_right;
        }
    }

    // like Fibonacci tree
    public void traverseInOrderBinaryTree(Node root) {
        if (root != null) {
            // deepest node on left has root.left as null ->
            // -> not in if -> System.out.println(root.key)
            //if(root.left != null) {
            traverseInOrderBinaryTree(root.left);
            //}
            System.out.println(root.key);
            // deepest node on left has root.right as null ->
            // -> not in if -> traverse back to its parent root
            //if(root.right != null) {
            traverseInOrderBinaryTree(root.right);
            //}
        }
    }
}



class Node {
    int key;
    Node left;
    Node right;

    public Node (int number) {
        key = number;
        left = null;
        right = null;
    }
}
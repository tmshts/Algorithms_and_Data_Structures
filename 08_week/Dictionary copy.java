import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.File;

public class Dictionary {
    
    public static void main(String [] args) {

        Scanner input = new Scanner(System.in);
        // hashmap for dictionary
        HashMap<String, String> dict = new HashMap<String, String>();

        // load dictionary from CSV file into HashMap
        try {
            FileReader fr = new FileReader("EnglishSpanish.csv"); 
            BufferedReader br = new BufferedReader(fr);
                String line;
                String[] values;
                while ((line = br.readLine()) != null) {
                    values = line.split(",");
                    String key_str = values[0];
                    String value_str = values[1];
                    dict.put(key_str, value_str);
                }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // create new tree
        BinaryTree tree = new BinaryTree();

		// add the dictionary from HashMap into binary tree
		for (Map.Entry<String, String> set : dict.entrySet()) {
            tree.add(set.getKey(), set.getValue());
		}

        // load text from txt file into String[]
        File file = new File("file.txt");

        // depends on the amount of lines
        int inputSize = 1000;
        String[] input_string_array = new String[inputSize];
        try {
            Scanner scan = new Scanner(file);
            int i = 0;
            while(scan.hasNextLine()){
                input_string_array[i] = scan.nextLine();
                i++;
            }
            scan.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("---Start of Binary Tree---");
        tree.traversePostOrderBinaryTree(tree.root);
        System.out.println("---End of Binary Tree---");
        // translate English word into Spanish word
        System.out.println("---Start of Translated Text---");
        for(int i = 0; i < input_string_array.length; i++) {
            if (input_string_array[i] != null) {
                tree.search(input_string_array[i]);
            }
        }
        System.out.println("---End of Translated Text---");

        Node result = tree.search("zygote");
        System.out.println(result.value);
        //System.out.println(dict);


        //System.out.println("Binary Tree:");
        //tree.traverseInOrderBinaryTree(tree.root);
        //tree.traversePreOrderBinaryTree(tree.root);
        int result_level = tree.depth(tree.root);
        int final_result;

        if (result_level <= 1) {
            final_result = 0;
        }
        else {
            // get height
            final_result = result_level - 1;
        }

        System.out.println("The resulting binary tree for dictionary has " + final_result + " level" + (final_result <= 1 ? "." : "s."));
    }
}

    class BinaryTree {
        // pointer to either left node or right node depending on value
        Node root;
    
        // constructor
        BinaryTree() {
            root = null;
        }
    
        public void add(String key_string, String value_string) {
            root = addRecursive(root, key_string, value_string);
        }
    
        // add node recursively
        private Node addRecursive(Node current, String key_string, String value_string) {

            if (current == null) {
                return new Node(key_string, value_string);
            }
            else {
                int compare = key_string.compareTo(current.key);

                if (compare < 0) {
                    current.left = addRecursive(current.left, key_string, value_string);
                }
                if (compare > 0) {
                    current.right = addRecursive(current.right, key_string, value_string);
                }
                else {
                    return current;
                }
            }
            return current;
        }
        
        // search for string
        public boolean search(String search_string) {
            return search(root, search_string);
        }
        private boolean search(Node current, String search_string) {
            //  search_string not found
            if (current == null) {
                System.out.println(search_string + " - no translation found");
                return false;
            }
            // keep searching
            else {
                int compare = search_string.compareTo(current.key);
                // search_string is on left
                if (compare < 0) {
                    //System.out.println("left");
                    return search(current.left, search_string);
                }
                // search_string is on right
                else if (compare > 0) {
                    //System.out.println("right");
                    return search(current.right, search_string);
                }
                // search_string == 0 -> found
                else {
                    // print translated word
                    System.out.println(current.value);
                    return true;
                }
            }
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

        public void traversePreOrderBinaryTree(Node root) {
            if (root != null) {
                System.out.print(root.key);
                System.out.print(", ");
                System.out.print(root.value);
                System.out.println();
                traversePreOrderBinaryTree(root.left);
                traversePreOrderBinaryTree(root.right);
            }
        }

        public void traverseInOrderBinaryTree(Node root) {
            if (root != null) {
                traverseInOrderBinaryTree(root.left);
                System.out.print(root.key);
                System.out.print(", ");
                System.out.print(root.value);
                System.out.println();
                traverseInOrderBinaryTree(root.right);
            }
        }

        public void traversePostOrderBinaryTree(Node root) {
            if (root != null) {
                traversePostOrderBinaryTree(root.left);
                traversePostOrderBinaryTree(root.right);
                System.out.print(root.key);
                System.out.print(", ");
                System.out.print(root.value);
                System.out.println();
            }
        }
    }

    class Node {
        String key;
        String value;
        Node left;
        Node right;
    
        public Node (String key_string, String value_string) {
            key = key_string;
            value = value_string;
            left = null;
            right = null;
        }
    }
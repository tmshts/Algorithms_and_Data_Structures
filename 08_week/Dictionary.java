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
        File file = new File("sentences.txt");

        // depends on the amount of lines
        int inputSize = 4;

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

        /*
        for(int i = 0; i < input_string_array.length; i++) {
            System.out.println(input_string_array[i]);
        }
        */

        String[] translated_string_array = new String[inputSize];
        for (int j = 0; j < input_string_array.length; j++) {
            String[] converted = input_string_array[j].split(" ");
            for(int i = 0; i < converted.length; i++) {
                tree.search(converted[i].toLowerCase());
            }
            System.out.println();
        }


        /*
        System.out.println("---Start of Binary Tree---");
        tree.traverseInOrderBinaryTree(tree.root);
        System.out.println("---End of Binary Tree---");
        // translate English word into Spanish word
        System.out.println("---Start of Translated Text---");
        for(int i = 0; i < input_string_array.length; i++) {
            if (input_string_array[i] != null) {
                tree.search(input_string_array[i]);
            }
        }
        System.out.println("---End of Translated Text---");

        */

        //Node result = tree.search("refit");
        //System.out.println(result.value);
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

            Node newNode = new Node(key_string, value_string);

            if (root == null) {
                root = newNode;
            }
            else {
                // start at the root
                Node current = root;
                Node parent;

                while (true) {
                    parent = current;
                    int compare = key_string.compareTo(current.key);
                    // go left
                    if (compare < 0) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            return;
                        }
                    }
                    // go right
                    else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            return;
                        }
                    }
                }
            }
        }
        
        public Node search(String search_string) {
            Node current = root;
            // no match
            while (search_string.compareTo(current.key) != 0) {
                int compare = search_string.compareTo(current.key);
                // search_string is on left
                if (compare < 0) {
                    //System.out.println("left");
                    current = current.left;
                }
                // search_string is on right
                else {
                    //System.out.println("right");
                    current = current.right;
                }
                if (current == null) {
                    System.out.print("--- " + search_string + " - no translation found --- ");
                    return null;
                }
            }
            System.out.print(current.value + " ");
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
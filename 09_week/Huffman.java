import java.util.*;


public class Huffman {
    
    public static void main(String[] args) {
    

        char[] asciiTable = new char[] {' ', '!', '"', '#', '%', '&', '\'', '(', ')', '*', '+', 
        ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', 
        '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your sentence: ");
        String input = scan.nextLine();

        char[] input_array = new char[input.length()];
        
        for (int i = 0; i < input.length(); i++){
            input_array[i] = input.charAt(i);
        }

        char[] letter_array = new char[input.length()];
        int[] frequency_array = new int[input.length()];

        int index = 0;

        //jk
        for(int j = 0; j < letter_array.length; j++) {
            // a b c d e f g h ....
            for(int a = 0; a < asciiTable.length; a++) {
                // j == j
                if (asciiTable[a] == input_array[j]) {
                    boolean flag = true;
                    int count = 0;
                    while(count < letter_array.length) {
                        // char alrady in letter_array
                        if (asciiTable[a] == letter_array[count]) {
                            // just update frequency
                            frequency_array[count] = frequency_array[count] + 1;
                            flag = false;
                            index++;
                        }
                        count++;
                    }
                    // chat not in letter_array -> add
                    if (flag) {
                        letter_array[index] = input_array[j];
                        frequency_array[index] = frequency_array[index] + 1;
                        index++;
                    }
                }
            }
        }

        // do not count empty char
        int increment = 0;
        for(int i = 0; i < frequency_array.length; i++) {
            if(letter_array[i] != '\0') {
                increment++;
            }
        }

        // creat new arrays for not-empty values
        char[] letter_array_cleared = new char[increment];
        int[] frequency_array_cleared = new int[increment];

        // fill the non-empty arrays with the values
        int index_index = 0;
        for(int i = 0; i < frequency_array.length; i++) {
            if(letter_array[i] != '\0') {
                letter_array_cleared[index_index] = letter_array[i];
                frequency_array_cleared[index_index] = frequency_array[i];
                index_index++;
            }
        }

        for(int i = 0; i < frequency_array_cleared.length; i++) {
            System.out.println("‘" + letter_array_cleared[i] + "‘" + " has a frequency of " + frequency_array_cleared[i]);
        }

        Tree tree;
        PriorityQueue <Tree> PQ = new PriorityQueue <Tree>();

        // add frequencies of letters into trees
        for(int i = 0; i < frequency_array_cleared.length; i++) {
            tree = new Tree();
            tree.addNode(letter_array_cleared[i]);
            tree.addFrequency(frequency_array_cleared[i]);
            PQ.add(tree);
        }

/*
to be or not to be
*/

        // create combo
        Tree lowest_1;
        Tree lowest_2;
        Tree comboTree;
        // keep iterating until there is just 1 tree in PriorityQueue = Huffman Tree
        while(PQ.size() > 1) {
            // poll - removes the smallest and return the smallest
            lowest_1 = PQ.poll();
            lowest_2 = PQ.poll();

            // create new tree as parent
            comboTree = new Tree();
            // create node parent with null as Character
            comboTree.addNode(null);

            // assigning left child node of root to node of tree
            comboTree.root.left = lowest_1.root;
            comboTree.root.right = lowest_2.root;

            comboTree.addFrequency(lowest_1.frequency + lowest_2.frequency);
            
            PQ.add(comboTree);
        }

        Tree tree_peak = PQ.peek();

        String keep_track = new String();

        // create hashmap ( key Character - value String ) and put it as argument
        // hashmap for dictionary
        HashMap<Character, String> dict = new HashMap<Character, String>();

        for(int i = 0; i < frequency_array_cleared.length; i++) {
            dict.put(letter_array_cleared[i], "");
        }

        tree_peak.traverseInOrderBinaryTree(tree_peak.root, keep_track, dict);

        for (int j = 0; j < input_array.length; j++) {
            for ( Map.Entry<Character, String> entry : dict.entrySet()) {
                Character key = entry.getKey();
                String value = entry.getValue();
                if (input_array[j] == key) {
                    System.out.print(value + " ");
                }
            }
        }
        System.out.println();
    }
}

/*
to be or not to be
 */


class Tree implements Comparable<Tree>{

    public Node root;
    public int frequency;

    // constructor
    public Tree(){
        root = null;
    }

    public void addFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void addNode(Character letter) {
        root = addRecursive(root, letter);
    }

    // add node recursively
    private Node addRecursive(Node current, Character letter) {
        return new Node(letter);
    }

/*
to be or not to be
*/

    // like Fibonacci tree
    public void traverseInOrderBinaryTree(Node root, String keep_track, Map<Character, String> dict) {
        if (root != null) {
            // deepest node on left has root.left as null ->
            // -> not in if -> System.out.println(root.key)
            traverseInOrderBinaryTree(root.left, keep_track+"0", dict);
            if(root.letter != null) {
                System.out.println(root.letter + " " + keep_track);
                // add 01 code for a specific letter to the hashmap
                for ( Map.Entry<Character, String> entry : dict.entrySet()) {
                    Character key = entry.getKey();
                    String value = entry.getValue();
                    dict.put(root.letter, keep_track);
                }
            }
            // deepest node on left has root.right as null ->
            // -> not in if -> traverse back to its parent root
            traverseInOrderBinaryTree(root.right, keep_track+"1", dict);
        }
    }

/*
to be or not to be
*/

    public int compareTo(Tree object){ //
        if(frequency-object.frequency>0){
            return 1;
         }
         else if(frequency-object.frequency<0){
            return -1;
         }
         return 0;
    }
}


class Node {
    public Character letter;
    public Node left;
    public Node right;

    public Node (Character letter) {
        this.letter = letter;
        left = null;
        right = null;
    }
}
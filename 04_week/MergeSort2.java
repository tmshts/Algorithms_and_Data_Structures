import java.util.HashMap;
import java.util.Scanner;

public class MergeSort2 {

    static char[] words_array;
    static HashMap<Character, Integer> letter_number = new HashMap<Character, Integer>();

    public static void main(String args[])
    {
        letter_number.put('a', 1);
        letter_number.put('b', 2);
        letter_number.put('c', 3);
        letter_number.put('d', 4);
        letter_number.put('e', 5);
        letter_number.put('f', 6);
        letter_number.put('g', 7);
        letter_number.put('h', 8);
        letter_number.put('i', 9);
        letter_number.put('j', 10);
        letter_number.put('k', 11);
        letter_number.put('l', 12);
        letter_number.put('m', 13);
        letter_number.put('n', 14);
        letter_number.put('o', 15);
        letter_number.put('p', 16);
        letter_number.put('q', 17);
        letter_number.put('r', 18);
        letter_number.put('s', 19);
        letter_number.put('t', 20);
        letter_number.put('u', 21);
        letter_number.put('v', 22);
        letter_number.put('w', 23);
        letter_number.put('x', 24);
        letter_number.put('y', 25);
        letter_number.put('z', 26);


        Scanner scanner_object = new Scanner(System.in);
        System.out.println("Enter word 1:");
        String word1 = scanner_object.nextLine();
        System.out.println("Enter word 2:");
        String word2 = scanner_object.nextLine();

        if (word1.length() > 100 || word1.length() < 1 || word2.length() > 100 || word2.length() < 1) {
            System.out.println("Length of words must be greater or equal to 1 and smaller or equal to 100");
            System.exit(1);
        }
        scanner_object.close();

        String word1_lowercase = word1.toLowerCase();
        String word2_lowercase = word2.toLowerCase();

        String words = word1_lowercase + word2_lowercase;

        words_array = new char[words.length()];

        for (int i = 0; i < words.length(); i++) {
            words_array[i] = words.charAt(i);
        }

        recMergeSort(words_array, 0, words.length() - 1);
    
        System.out.println("merged:");
        printArray(words_array);
    }

    public static void recMergeSort(char[] words_array, int lowerBound,
    int upperBound) {

        if(lowerBound == upperBound) // if range is 1,
            return; // no use sorting
        else{
            // find midpoint
            int mid = (lowerBound+upperBound) / 2;
            // sort low half
            recMergeSort(words_array, lowerBound, mid);
            // sort high half
            recMergeSort(words_array, mid+1, upperBound);
            // merge them
            merge(words_array, lowerBound, mid+1, upperBound);
        }
    }
    
    public static void merge(char[] words_array, int first,
    int second, int upperBound) {

        int j = 0; // workspace index
        int lowerBound = first;
        int mid = second - 1;
        int n = upperBound-lowerBound+1; // # of items

        char[] workSpace = new char[words_array.length];

        while(first <= mid && second <= upperBound) { //halves not empty
            if( letter_number.get(words_array[first]) < letter_number.get(words_array[second])) {
                workSpace[j] = words_array[first++];
                j++;
            }
            else {
                workSpace[j++] = words_array[second++];
            }
        }
        while(first <= mid) { //check first half for remaining
            workSpace[j++] = words_array[first++];
        }
        while(second <= upperBound) //check second half for remaining
            workSpace[j++] = words_array[second++];

        
        for(j=0; j<n; j++) {
            words_array[lowerBound+j] = workSpace[j]; //copy the workspace back
        }
    } // end merge()

    public static void printArray(char char_array[])
    {
        int n = char_array.length;
        for (int i = 0; i < n; ++i)
            System.out.print(char_array[i]);
        System.out.println();
    }
    
}
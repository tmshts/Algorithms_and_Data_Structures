import java.util.Scanner;

public class MergeSort1 {

    static String merged_word = new String();

    public static void main(String args[])
    {
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

        merge(word1_lowercase, word2_lowercase);
  
        System.out.println("Merged:");
        printArray(merged_word);
    }

    public static void merge(String word1, String word2) {

        int lowerBound = 0;
        String workSpace = new String();

        while(lowerBound <= word1.length()-1 && lowerBound <= word2.length()-1) {
            workSpace = workSpace + word1.charAt(lowerBound) + word2.charAt(lowerBound); 
            lowerBound++;
        }

        if (word1.length() > word2.length()) {
            String rest_word1 = word1.substring(word2.length(), word1.length());
            workSpace = workSpace + rest_word1;
        } else {
            String rest_word2 = word2.substring(word1.length(), word2.length());
            workSpace = workSpace + rest_word2;
        }
        merged_word = workSpace;
    }


    public static void printArray(String arr)
    {
        int n = arr.length();
        for (int i = 0; i < n; ++i)
            System.out.print(arr.charAt(i));
        System.out.println();
    }
}
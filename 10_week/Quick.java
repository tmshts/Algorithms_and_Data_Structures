import java.math.BigInteger;
import java.util.*;


public class Quick {
    
    private static int counter = 0;
    private static int[][] array_collatz;

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();

        int number = 160;

        array_collatz = new int[number][2];

        for (int i = 1; i < number; i++) {
            int result = collatz(i);
            array_collatz[i][0] = i;
            array_collatz[i][1] = result;
        }

        System.out.println("array_collatz");
        for (int i = 1; i < number; i++) {
            System.out.println(array_collatz[i][0] + " " + array_collatz[i][1]);
        }
        System.out.println();

        recQuickSort(array_collatz, 0, number - 1);

        System.out.println("array_collatz sorted");
        for (int i = 0; i < array_collatz.length; i++) {
            System.out.println(array_collatz[i][0] + " : " + array_collatz[i][1]);
        }
        System.out.println();
        
        System.out.println(array_collatz[input][0] + " " + array_collatz[input][1]);
    }

    
    public static void recQuickSort(int[][] array_collatz, int left, int right) {
        if(left < right) {
            int partition = partitionIt(array_collatz, left, right);
            recQuickSort(array_collatz, left, partition - 1);
            recQuickSort(array_collatz, partition + 1, right);
        }
    }

    public static int partitionIt(int[][] array_collatz, int left, int right) {
        //int leftPtr = left - 1;
        //int rightPtr = right;
        // choose the pivot
        int pivot = array_collatz[right][1];
        // 
        int leftPtr = (left - 1);

        for(int j = left; j < right; j++) {
            // pivot is equal to right -> for loop < but if including pivot
            if (array_collatz[j][1] <= pivot) {
                // increment index of smaller element
                leftPtr++;
                // swap
                int[] temp = array_collatz[leftPtr]; 
                array_collatz[leftPtr] = array_collatz[j]; 
                array_collatz[j] = temp;
            }
        }
        // swap
        int[] temp = array_collatz[leftPtr + 1]; 
        array_collatz[leftPtr + 1] = array_collatz[right]; 
        array_collatz[right] = temp;

        return (leftPtr + 1);
    }
    

    public static int collatz(int number) {
        counter++;

        // base case
        if (number == 1) {
            int output = counter;
            counter = 0;
            return output;
        }

        // recursive step
        else {
            if (number % 2 == 0) {
                return collatz(number / 2);
            }
            else {
                return collatz(number * 3 + 1);
            }
        }
    }
}

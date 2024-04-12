import java.util.Random;
import java.util.Scanner;

public class MonteCarlo {
    
    public static void main(String[] args) {

        long trials = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of trials");
        trials = scan.nextLong();
        scan.close();
        //System.out.println(trials);
        double result = estimation(trials);
        //System.out.println(String.format("%.20f", result));
        String result_formated = String.format("%.100f", result);
        System.out.println("Expected return on buying a ticket using a Monte Carlo with " + trials + " is " + result_formated + "." );
    }

    public static double estimation (long trialss) {
        // instance of Random class
        Random rnd = new Random();
        int[] winning_numbers = {23, 10, 21, 30, 3};
        int[] winning_lucky = {4, 2};
        long successes = 0;
        int random_normal = 0;
        int random_lucky = 0;

        int normal_numbers[] = new int[winning_numbers.length];
        int lucky_numbers[] = new int[winning_lucky.length];

        for (int u = 0; u < winning_numbers.length; u++) {
            // generate random integers in range 0 - 50
            random_normal = rnd.nextInt(50) + 1;
            normal_numbers[u] = random_normal;
        }

        for (int o = 0; o < winning_lucky.length; o++) {
            // generate random integers in range 0 - 12
            random_lucky = rnd.nextInt(12) + 1;
            lucky_numbers[o] = random_lucky;
        }

        int k = 0;
        int j = 0;
        int count = 0;

        // simulation itself
        for (int i = 0; i < trialss; i++) {
            // handle winning numbers
            for (int w = 0; w < winning_numbers.length; w++) {
                while (k < winning_numbers.length) {
                    if (winning_numbers[w] == normal_numbers[k]) {
                        count++;
                    }
                    k++;
                }
                k = 0;
            }

            // handle winning lucky
            for (int q = 0; q < winning_lucky.length; q++) {
                while (j < winning_lucky.length) {
                    if (winning_lucky[q] == lucky_numbers[j]) {
                        count++;
                    }
                    j++;
                }
                j = 0;
            }

            // if won -> successes++
            if (count == (winning_numbers.length + winning_lucky.length)) {
                successes++;
            }
            count = 0;
        }

        double estimation = successes / (double) trialss;
        return estimation;

    }

}

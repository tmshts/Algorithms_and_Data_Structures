import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Attendance {

    static List<Integer> absent_array = new ArrayList<>();

    public static void main (String [] args) {
        Scanner scanner_object = new Scanner(System.in);
        System.out.println("INPUT:");
        String input = scanner_object.nextLine();
        String lower_input = input.toLowerCase();
        if (lower_input.length() == 0) {
            System.out.println("Please enter a sequence of strings");
            System.exit(0);
        }

        Boolean result = calculate(lower_input, absent_array);

        if (result) {
            System.out.println("PASS");          
        }
        if (!result) {
            System.out.println("FAIL");          
        }
        scanner_object.close();
    }

    public static Boolean calculate(String input, List<Integer> absent_array) {
        // base cases
        if (input.length() == 1) {
            return true;
        }
        if (input.length() == 2) {
            if (input.charAt(1) == 'a') {
                absent_array.add((1));
            }
            if (input.charAt(0) == 'a') {
                absent_array.add((1));
            }
            int size_of_absent_array = absent_array.size();
            if (size_of_absent_array >= 2 ) {
                return false;
            }
            else {
                return true;
            }
        }

        // rule to select
        if (input.charAt(0) == 'a') {
            absent_array.add((1));
        }
        if (input.charAt(0) == 'l' && input.charAt(1) == 'l' && input.charAt(2) == 'l') {
            //System.out.println("Should be late");
            return false;     
        }

        // recursion set
        String inter_string = input.substring(1, input.length());
        //System.out.println("inter_string");
        //System.out.println(inter_string);
        //System.out.println("inter_string.length()");
        //System.out.println(inter_string.length());
        return calculate(inter_string, absent_array);           
    }
}
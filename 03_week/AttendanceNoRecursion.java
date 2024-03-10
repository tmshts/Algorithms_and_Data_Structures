import java.util.Scanner;

public class AttendanceNoRecursion {
    public static void main (String [] args) {
        Scanner scanner_object = new Scanner(System.in);
        System.out.println("INPUT:");
        String input = scanner_object.nextLine();
        String lower_input = input.toLowerCase();
        Boolean result = calculate(lower_input);
        if (result) {
            System.out.println("PASS");          
        }
        if (!result) {
            System.out.println("FAIL");          
        }
        scanner_object.close();
    }

    public static Boolean calculate(String input) {
        Integer absent = 0;
        Integer late = 0;
        for (int i = 0; i < input.length(); i++ ) {
            if (input.charAt(i) == 'a') {
                absent = absent + 1;
                //System.out.println("absent");
                //System.out.println(absent);
            }
            else {
                absent = 0;
                //System.out.println("not absent");
                //System.out.println(absent);
            }

            if (input.charAt(i) == 'l') {
                late = late + 1;
                //System.out.println("late");
                //System.out.println(late);
            }
            else {
                late = 0;
                //System.out.println("not late");
                //System.out.println(late);
            }

            if (absent == 2 || late == 3) {
                //System.out.println("absent");
                //System.out.println(absent);
                return false;
            }
        }
        return true;
    }
}

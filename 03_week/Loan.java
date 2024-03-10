import java.util.Scanner;

public class Loan {

    static Integer months = 0;

    public static void main (String [] args) {
        Scanner scanner_object = new Scanner(System.in);
        System.out.println("Enter the loan that needs to be paid off:");
        Float loan = scanner_object.nextFloat();
        System.out.println("Enter anual interest rate:");
        Float rate = scanner_object.nextFloat();
        System.out.println("Enter monthly loan repayment:");
        Integer repayment = scanner_object.nextInt();
        Integer result = loanCalculate(loan, rate, repayment);
        System.out.println("It takes you " + result + " months to pay off your loan of " + loan + " Eur.");
        scanner_object.close();
    }

    public static Integer loanCalculate(float loan, float rate, int repayment) {
        // base case
        if ( loan <= 0 ) {
            return months;
        }

        // logic
        Float monthly_rate = rate/12;
        //System.out.println("monthly_rate");
        //System.out.println(monthly_rate);
        Float inter_result = loan * (1 + (monthly_rate/100));
        //System.out.println("inter_result");
        //System.out.println(inter_result);
        Float rest = (float) (inter_result - repayment);
        //System.out.println("rest");
        //System.out.println(rest);
        months = months + 1;
        return loanCalculate(rest, rate, repayment);
    }
}

import java.util.Scanner;

public class LinkedList {
    // pointer to first link
    private static Link first;

    // constructor and first is null
    public LinkedList(){
        first = null;
    }

    public static boolean isEmpty() {
        return (first == null);
    }

    public static void insertHead(String input) {
        Link newLink = new Link(input);
        if (isEmpty()) {
            first = newLink;
        }
        else {
            newLink.next = first;
            first = newLink;
        }

        // Input: Maynooth is great! -> Output: Maynooth is great!
        // Link newLink = new Link(input);
        // newLink.next = first;
        // above two lines put at the end
    }

    public static void display() {
        // start with first link
        Link current = first;
        while(current != null) {
            current.displayLink();
            current=current.next;
        }
    }

    public static void main(String [] args) {
        Scanner scanner_object = new Scanner(System.in);
        String end_string = "";
        while(!end_string.equals("END")) {
            System.out.println("Enter a word");
            String input = scanner_object.nextLine();
            if(!input.equals("END")) {
                insertHead(input);
            }
            end_string = input;
        }
        /* solution b from demonstrator
        while(true) {
            System.out.println("Enter a word");
            String input = scanner_object.nextLine();
            if(input.equals("END")) {
                break;
            }
            insertHead(input);
        }
        */
        /* solution c from demonstrator
        System.out.println("Enter a word");
        String input = scanner_object.nextLine();
        while(!input.equals("END")) {
            insertHead(input);
            System.out.println("Enter a word");
            input = scanner_object.nextLine();
        }
        */
        scanner_object.close();
        System.out.println("Linked list:");
        display();
    }
}

class Link {

    // data
    public String data;
    // pointer
    public Link next;

    // constructor
    public Link(String datain) {
        // initialize data
        data = datain;
        // next is set automatically to null
        next = null;
    }

    public void displayLink() {
        System.out.println(this.data);
    }
}
import java.util.Scanner;

public class LinkedListNumbers {
    // pointer to first link
    private static Link first;

    // constructor and first is null
    public LinkedListNumbers(){
        first = null;
    }

    public static boolean isEmpty() {
        return (first == null);
    }

    public static void insertHead(int input) {
        Link newLink = new Link(input);
        if (isEmpty()) {
            first = newLink;
        }
        else {
            newLink.next = first;
            first = newLink;
        }
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
        while(true) {
            System.out.println("Enter a number");
            int input = scanner_object.nextInt();
            if(input == 2147483647) {
                break;
            }
            insertHead(input);
        }

        scanner_object.close();
        System.out.println("Linked list:");
        display();
    }
}

class Link {

    // data
    public int data;
    // pointer
    public Link next;

    // constructor
    public Link(int datain) {
        // initialize data
        data = datain;
        // next is set automatically to null
        next = null;
    }

    public void displayLink() {
        System.out.println(this.data);
    }
}
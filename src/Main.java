import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
       Calendar cal1 = Calendar.getInstance();

        Book book1 = new Book("Calvin and Hobbes", "BIll Watterson", "comic", true);
        CheckedOutBook book2 = new CheckedOutBook ("Calvin and Hobbes", "Bill Watterson", "comic",false, Calendar.getInstance());


        System.out.println(book1.toString());
        System.out.println(book2.toString());

    }

}

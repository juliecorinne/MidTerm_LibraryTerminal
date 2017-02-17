import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class BookDB {
    private ArrayList<Book> collection;

    //constructor
    public BookDB() {
        this.collection = new ArrayList<Book>();
        this.collection.sort(Comparator.comparing(Book::getAuthor));

    }

    public ArrayList<Book> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<Book> collection) {
        this.collection = collection;
    }

    public void printBook(Book book) {
        System.out.println(book);
    }

    public void addBook(Book book) {
        this.collection.add(book);
    }

    public void sortDB() {

        this.collection.sort(Comparator.comparing(Book::getAuthor));
    }

    public void selectBook() {
        String input = Validate.getString("Enter ISBN or Book Title to check out: ");

        for (Book b : this.collection) {
//            notify();
            if (input.equalsIgnoreCase(b.getIsbn()) || input.equalsIgnoreCase(b.getTitle())) {
                if (b.getStatus()) {
                    System.out.println("Book is available!");
                    System.out.println(b);
                    input = Validate.validateYesOrNo("Would you like to check out this book? (Y/N) ");
                    if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                        b.checkInOut();
                        System.out.println(b.getTitle() + " is due back by " + b.getDateString());
                    }
                } else {
                    System.out.println("Book is already checked out!");
                }
            }
        }
    }

    public void checkOut() {
        String input = Validate.validateYesOrNo("Would you like to checkout one of these books? (Y/N)");
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            selectBook();
        }
    }


    //Displays entire book list
    public void displayBooks() {
        for (Book b : this.collection) {
            System.out.println(b);
        }
        checkOut();
    }


    /*  Method gather string from user.
        Matches string with string contained in Book Author and prints out information for these books.
        Enters into BookDB checkout method
        Prints message if no matches appear.
    */
    public void searchByKeyword() {
        boolean keyword = false;
            String input = Validate.getString("Enter book keyword: ");
            while (!keyword) {
                for (Book b : this.collection) {
                    if (b.getCategory().toLowerCase().contains(input.toLowerCase()) || b.getAuthor().toLowerCase().contains(input.toLowerCase()) || b.getTitle().toLowerCase().contains(input.toLowerCase())) {
                        System.out.println(b);
                        keyword = true;
                    }
                }

                if (!keyword) {
                    System.out.println("Sorry! Invalid entry. Please try again.");
                    input = Validate.getString("Enter book keyword: ");

                } else {
                    checkOut();
                }
            }

    }

    /*  Method gather string from user.
        Matches string with string content in Book Author and prints out information for these books.
        Enters into BookDB checkout method
        Prints message if no matches appear.
    */
    public void searchByAuthor() {
        String input = Validate.getString("Enter book author: ");
        for (Book b : this.collection) {
            if (b.getAuthor().toLowerCase().contains(input.toLowerCase())) {
                System.out.println(b);
            }
        }
        checkOut();
    }

    /*  Method gather string from user.
        Matches string with string content in Book Category and prints out information for these books.
        Enters into BookDB checkout method
        Prints message if no matches appear.
     */
    public void searchByCategory() {
        String input = Validate.getString("Enter book category: ");
        for (Book b : this.collection) {
            if (b.getCategory().toLowerCase().contains(input.toLowerCase())) {
                System.out.println(b);
            }
        }
        checkOut();
    }


    public void returnBook() {
        for (Book b : this.collection) {
            if (!b.getStatus()) {
                System.out.println(b);
            }
        }
        String input = Validate.getString("Enter ISBN or Book Title to return: ");
        for (Book book : this.collection) {
            if (input.equalsIgnoreCase(book.getIsbn()) || input.equalsIgnoreCase(book.getTitle())) {
                System.out.println(book);
                input = Validate.validateYesOrNo("Is this the book you would like to return? (Y/N) ");
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                    book.checkInOut();
                    System.out.println("Thank you for returning " + book.getTitle() + "!");
                }
            } else {
                System.out.println("No books currently checked out!");
                return;
            }
        }
    }

    //gathers userinput for book title
    public String getUserTitle() {
        return Validate.getString("Enter book title: ");
    }

    //gathers user input for book author
    public String getUserAuthor() {
        return Validate.getString("Enter book author: ");
    }

    //gathers user input for book genre
    public String getUserGenre() {
        return Validate.getString("Enter book genre: ");
    }

    public void addUserBook() {
        this.collection.add(new Book(getUserTitle(), getUserAuthor(), getUserGenre(), Book.setRandomISBN(), true));
        System.out.println("Thank you for your donation!");
    }


}


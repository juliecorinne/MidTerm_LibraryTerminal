import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookDB {
    private ArrayList<Book> collection;

    //constructor
    public BookDB() {
        this.collection = new ArrayList<Book>();
    }

    public ArrayList<Book> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<Book> collection) {
        this.collection = collection;
    }

    public void addBook(Book book) {
        this.collection.add(book);
    }


    //Displays entire book list
    public void displayBooks(){
        for(Book b: this.collection){
            System.out.println(b);
        }
        checkOut();
    }

    public void searchByKeyword(){
        String input = Validate.getString("Enter book keyword: ");
        for(Book b: this.collection){
            if (b.getCategory().toLowerCase().contains(input.toLowerCase()) || b.getAuthor().toLowerCase().contains(input.toLowerCase()) || b.getTitle().toLowerCase().contains(input.toLowerCase())){
                System.out.println(b);
            }
            checkOut();
        }
    }

    public void searchByAuthor(){
        String input = Validate.getString("Enter book author: ");
        for(Book b: this.collection){
            if(b.getAuthor().toLowerCase().contains(input.toLowerCase())){
                System.out.println(b);
            }
            checkOut();
        }
    }

    public void searchByCategory() {
        String input = Validate.getString("Enter book category: ");
        for (Book b : this.collection) {
            if (b.getCategory().toLowerCase().contains(input.toLowerCase())) {
                System.out.println(b);
            }
            checkOut();
        }
    }

    public void checkAvailable(Book b){
        if(b.isStatus()){
            System.out.println("Book is not available");
        } else{
            System.out.println("Book is available");
        }
    }

    public Book selectBook(){
        String input = Validate.getString("Enter ISBN or Book Title to check out: ");
        for(Book b: this.collection) {
            if (input.equalsIgnoreCase(b.getIsbn()) || input.equalsIgnoreCase(b.getTitle())) {
                System.out.println(b);
                if (b.isStatus()) {
                    System.out.println("Book is available!");
                    switchStatus(b);
                    return b;
                }
                System.out.println("Book is not available!");

            }
        }
        return null;
    }

    public void checkOut(){
        String input = Validate.validateYesOrNo("Would you like to checkout a book? (Y/N)");
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
            selectBook();
        }
    }

    public void switchStatus(Book b){
        if(b.isStatus()){
            b.setStatus(false);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DAY_OF_MONTH, 14);
            b.setDate(now);
            CheckedOutBook book;
            book = new CheckedOutBook(b.getTitle(), b.getAuthor(), b.getCategory(), b.getIsbn(), b.isStatus(),b.getDate());
            b = book;
            System.out.println(b.toString());

        } else{
            b.setStatus(true);
            Book b1 = b;
        }
    }

}


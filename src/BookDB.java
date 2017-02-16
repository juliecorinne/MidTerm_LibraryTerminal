import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BookDB {
    private ArrayList<Book> collection;
    private Path filePath = Paths.get("bookDB.txt");
    private File bookDBFile = filePath.toFile();

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

    /*  writes bookDB.txt before app closes.
        formats .txt file to allow loadBookDB method to read again upon opening program again.
        @param tempList is used to call bookDB data that will be stored in .txt file
     */

    public void searchByKeyword(){
        String input = Validate.getString("Enter book keyword: ");
        for(Book b: this.collection){
            if (b.getCategory().toLowerCase().contains(input.toLowerCase()) || b.getAuthor().toLowerCase().contains(input.toLowerCase()) || b.getTitle().toLowerCase().contains(input.toLowerCase())){
                System.out.println(b);
            }
            selectBook();
        }
    }

    public void searchByAuthor(){
        String input = Validate.getString("Enter book author: ");
        for(Book b: this.collection){
            if(b.getAuthor().toLowerCase().contains(input.toLowerCase())){
                System.out.println(b);
            }
            selectBook();
        }
    }

    public void searchByCategory() {
        String input = Validate.getString("Enter book category: ");
        for (Book b : this.collection) {
            if (b.getCategory().toLowerCase().contains(input.toLowerCase())) {
                System.out.println(b);
            }
            selectBook();
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
        String input = Validate.getString("Enter ISBN or Book Title to select: ");
        for(Book b: this.collection){
        if(input.equalsIgnoreCase(b.getIsbn()) || input.equalsIgnoreCase(b.getTitle())){
            System.out.println(b);
            return b;
            }
        }
            return null;
    }


    public void writeBookDB(BookDB tempList) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(bookDBFile));

            for (Book book : this.collection) {
                out.println(book.getAuthor());
                out.println(book.getTitle());
                out.println(book.getCategory());
                out.println(String.valueOf(book.isStatus()));
                if (book instanceof CheckedOutBook) {
                    out.println(((CheckedOutBook) book).getDateString());
                } else {
                    out.println("");
                }
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


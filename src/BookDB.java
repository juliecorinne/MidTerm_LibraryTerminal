import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookDB {
    private ArrayList<Book> collection;
    private static Path filePath = Paths.get("bookDB.txt");
    private static File bookDBFile = filePath.toFile();

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

    public void returnBook(){
        for (Book b: this.collection){
            if (!b.isStatus()){
                System.out.println(b);
            }
        }
        String input = Validate.getString("Enter ISBN or Book Title to return: ");
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
                    System.out.println(b.isStatus());
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
        if(b.isStatus()) {
            b.setStatus(false);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DAY_OF_MONTH, 14);
            b.setDate(now);
            System.out.println(b.getDate().getTime());

        } else {
            b.setStatus(true);
            Book b1 = b;
        }
    }



    //ArrayList of Book objects to store Book objects added from .txt File
    public static BookDB loadBookDB() {

        BookDB tempList = new BookDB();
        try {
            FileReader r = new FileReader(bookDBFile);
            LineNumberReader lnr = new LineNumberReader(r);
            //Calls last line number in text file
            lnr.skip(Long.MAX_VALUE);
            //add 1 becuase LineNumberReader starts at 0
            long length = lnr.getLineNumber() + 1;
            lnr.close();

            BufferedReader reader = new BufferedReader(new FileReader(bookDBFile));

            int numFields = 6; //Book class has 6 fields
            for (int i=1; i<length/numFields; i++) {
                String title = reader.readLine();
                String author = reader.readLine();
                String genre = reader.readLine();
                String isbn = reader.readLine();
                //reads string from .txt file, converts to boolean. will return false for any strings that are not "true"
                String s = reader.readLine();
                boolean status = Boolean.parseBoolean(s);
                //JB tempList.addBook(new Book(title, author, category, date, status));
                //reads string from .txt file, converts to Calendar object.
                String d = reader.readLine();
                if(d.equalsIgnoreCase("")){
                    tempList.addBook(new Book(title, author, genre, isbn, status));
                }else {
                    Calendar date = stringToCalendar(d);
                    tempList.addBook(new CheckedOutBook(title, author, genre, isbn, status, date));
                }

            }

            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tempList;
    }





    /*  Method converts String object to calendar object.
        String must be in specified SimpleDateFormat, otherwise throws ParseException
        @param String string specifies String to be converted
     */
    public static Calendar stringToCalendar(String string) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            cal.setTime(sdf.parse(string));
        } catch (ParseException e) {
            cal = null;
        }

        return cal;
    }

    /*  writes bookDB.txt before app closes.
        formats .txt file to allow loadBookDB method to read again upon opening program again.
        @param tempList is used to call bookDB data that will be stored in .txt file
     */
    public void writeBookDB() {

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


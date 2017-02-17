
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookTextFile {
    private static Path filePath = Paths.get("bookDB.txt");
    private static File bookDBFile = filePath.toFile();

    //ArrayList of Book objects to store Book objects added from .txt File
    public static BookDB loadBookDB() {

        BookDB tempList = new BookDB();
        try {
            FileReader r = new FileReader(bookDBFile);
            LineNumberReader lnr = new LineNumberReader(r);
            //Calls last line number in text file
            lnr.skip(Long.MAX_VALUE);
            //add 1 becuase LineNumberReader starts at 0
            long length = lnr.getLineNumber();
            lnr.close();

            BufferedReader reader = new BufferedReader(new FileReader(bookDBFile));

            int numFields = 6; //Book class has 6 fields
            for (int i = 0; i < length / numFields; i++) {
                String title = reader.readLine();
                String author = reader.readLine();
                String genre = reader.readLine();
                String isbn = reader.readLine();
                //reads string from .txt file, converts to boolean. will return false for any strings that are not "true"
                String s = reader.readLine();
                boolean status = Boolean.parseBoolean(s);
                String d = reader.readLine();
                //readers string from .txt file. If String is empty, creates Book Object without dueDate
                if (d.equalsIgnoreCase("")) {
                    tempList.addBook(new Book(title, author, genre, isbn, status));
                    //reads string from .txt file, converts to Calendar object. If book is checked out, creates Book object with dueDate
                } else {
                    Calendar date = stringToCalendar(d);
                    tempList.addBook(new Book(title, author, genre, isbn, status, date));
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
    public static void writeBookDB(BookDB bookDB) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(bookDBFile));

            for (Book book : bookDB.getCollection()) {
                out.println(book.getTitle());
                out.println(book.getAuthor());
                out.println(book.getCategory());
                out.println(book.getIsbn());
                out.println(String.valueOf(book.getStatus()));
                //Prints date as formatted string to file if book is available
                if (book.getDueDate() != null) {
                    out.println(book.getDateString());
                    //Prints new line if book is checked out
                } else {
                    out.println();
                }
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}







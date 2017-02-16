
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookTextFile {
    //Creates new .txt file if it doesn't exist already.
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
            long length = lnr.getLineNumber() + 1;
            lnr.close();

            BufferedReader reader = new BufferedReader(r);

            int numFields = 5; //Book class has 5 fields
            for (int i=0; i<length/numFields; i++) {
                String title = reader.readLine();
                String author = reader.readLine();
                String genre = reader.readLine();
                //reads string from .txt file, converts to Calendar object.
                String d = reader.readLine();
                Calendar date = stringToCalendar(d);
                //reads string from .txt file, converts to boolean. will return false for any strings that are not "true"
                String s = reader.readLine();
                boolean status = Boolean.parseBoolean(s);
                tempList.addBook(new Book(title, author, genre, date, status));
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
}







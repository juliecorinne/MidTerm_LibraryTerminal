//import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by julieschneider on 2/15/17.
 */
public class CheckedOutBook extends Book {

    private Calendar date;

    public CheckedOutBook(String title, String author, String category, Calendar dueDate, boolean status, Calendar date) {
        super(title, author, category, dueDate, status);
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }



}

//import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by julieschneider on 2/15/17.
 */
public class CheckedOutBook extends Book {

    private Date date;

    public CheckedOutBook(String title, String author, String category, Calendar dueDate, boolean status, Date date) {
        super(title, author, category, dueDate, status);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}

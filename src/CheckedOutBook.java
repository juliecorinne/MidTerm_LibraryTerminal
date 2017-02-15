//import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by julieschneider on 2/15/17.
 */
public class CheckedOutBook extends Book {

    private boolean checkedOut;
    private Date date;

    public CheckedOutBook(String title, String author, String category, Calendar dueDate, boolean status, boolean checkedOut, Date date) {
        super(title, author, category, dueDate, status);
        this.checkedOut = checkedOut;
        this.date = date;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

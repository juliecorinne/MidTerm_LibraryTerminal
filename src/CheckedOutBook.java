//import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by julieschneider on 2/15/17.
 */
public class CheckedOutBook extends Book {

    private Calendar date;

    public CheckedOutBook(String title, String author, String category,  boolean status, Calendar date) {
        super(title, author, category, status);
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    //converts Calendar object to String object
    public String getDateString() {
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyy");
        return format1.format(this.date.getTime());
    }

    @Override
    public  String  toString(){


       // SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
       // System.out.println(date.getTime());
        // Output "Wed Sep 26 14:23:28 EST 2012"

        //String formatted = format1.format(date.getTime());
      //  System.out.println(formatted);
        // Output "2012-09-26"




return  super .toString() + String.format(" %-10s", getDateString() );




    }


}

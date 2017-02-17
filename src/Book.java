import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Book {
    private String title;
    private String author;
    private String category;
    private String isbn;
    private boolean status;
    private Calendar dueDate;

    //constructor
    public Book(String title, String author, String category, String isbn, boolean status) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.status = status;
    }
    //overloaded constructor to allow book to contain dueDate when necessary
    public Book(String title, String author, String category, String isbn, boolean status, Calendar dueDate) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.status = status;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static String setRandomISBN(){
        Random random = new  Random();
        return String.format("%04d", random.nextInt(10000));
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void checkInOut(){
        if (status){
            setStatus(false);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DAY_OF_MONTH, 14);
            setDueDate(now);
        } else{
            setStatus(true);
            setDueDate(null);
        }
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar date) {
        this.dueDate = date;
    }

    public String getDateString() {
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyy");
        return format1.format(this.dueDate.getTime());
    }

    @Override
    public String toString() {
        String checkedOut = "Checked Out";
        if (status) {
            return String.format("%-55s%-20s%-20s%-10s", title, author, category, isbn);
        } else {
            return String.format("%-55s%-20s%-20s%-10s%-20s%-20s", title, author, category, isbn, "Checked Out", getDateString());
        }
    }

}

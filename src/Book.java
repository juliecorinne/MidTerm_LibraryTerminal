import java.util.Calendar;

public class Book {
    private String title;
    private String author;
    private String category;

    private boolean status;

    public Book(String title, String author, String category, boolean status) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public  String  toString() {
        String checkedOut = "";
        if (status == true){
            checkedOut = "Available";
        }else {
            checkedOut = "Checkedout";
        }

        return String.format("%-10s %-10s %-10s %-10s", title, author, category, checkedOut);

    }

}

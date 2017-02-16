import java.util.Calendar;

public class Book {
    private String title;
    private String author;
    private String category;
    private String isbn;
    private boolean status;

    public Book(String title, String author, String category, String isbn, boolean status) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public  String  toString() {
        String checkedOut = "Checked Out";
        if (status){
            checkedOut = "";
        }

        return String.format("%-50s %-20s %-20s %-10s %-20s", title, author, category, isbn, checkedOut);

    }

}

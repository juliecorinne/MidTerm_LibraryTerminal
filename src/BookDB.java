import java.util.ArrayList;

public class BookDB {
    private ArrayList<Book> collection;

    //constructor
    public BookDB(){
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





}

public class LibraryApp {
    static BookDB bookDB = new BookDB();

    public static void displayMenu(){
        System.out.println("Grand Circus Library");
        System.out.println("1. Display list of books");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Title");
        System.out.println("4. Search by Genre");
        System.out.println("5. Quit");
    }

    public static void userSelection(){
        int userChoice = Validate.validateInt("Select option (1-6): ", 1, 5);
        switch (userChoice) {
            case 1:
                bookDB.displayBooks();
                break;
            case 2:
                bookDB.searchByAuthor();
                break;
            case 3:
                bookDB.searchByKeyword();
                break;
            case 4:
                bookDB.searchByCategory();
                break;
            case 5:
                BookTextFile.writeBookDB(bookDB);
                break;

        }
    }

    public static void main(String[] args) {
        bookDB = BookTextFile.loadBookDB();
        displayMenu();
        userSelection();
        BookTextFile.writeBookDB(bookDB);
    }



}

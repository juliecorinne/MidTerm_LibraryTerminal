public class LibraryApp {
    static BookDB bookDB = new BookDB();
    static boolean continueLoop = true;

    public static void displayMenu() {
        System.out.println("Hello! Welcome to the grand Circus Library Database.");
        System.out.println("1. Display all books");
        System.out.println("2. Search by Keyword");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Genre");
        System.out.println("5. Return book");
        System.out.println("6. Donate book");
        System.out.println("7. Quit");
    }

    public static void userSelection() {
        int userChoice = Validate.validateInt("Select option (1-7): ", 1, 7);
        switch (userChoice) {
            case 1:
                bookDB.displayBooks();
                break;
            case 2:
                bookDB.searchByKeyword();
                break;
            case 3:
                bookDB.searchByAuthor();
                break;
            case 4:
                bookDB.searchByCategory();
                break;
            case 5:
                bookDB.returnBook();
                break;
            case 6:
                bookDB.addUserBook();
                break;
            case 7:
                BookTextFile.writeBookDB(bookDB);
                System.out.println("Thanks! Come again!");
                System.exit(0);
                break;

        }
    }

    public static void main(String[] args) {
        bookDB = BookTextFile.loadBookDB();
        String userString;

        while (continueLoop) {
            bookDB.sortDB();
            displayMenu();
            userSelection();
            userString = Validate.validateYesOrNo("Would you like to return to main menu? (Y/N) ");
            if (userString.equalsIgnoreCase("n")) {
                continueLoop = false;
            }
        }
        BookTextFile.writeBookDB(bookDB);
        System.out.println("Thanks! Come again!");
    }
}

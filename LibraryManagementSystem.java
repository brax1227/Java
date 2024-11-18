import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return !isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

public class LibraryManagementSystem {
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add book\n2. View books\n3. Borrow book\n4. Return book\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> viewBooks();
                case 3 -> borrowBook(scanner);
                case 4 -> returnBook(scanner);
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added.");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }

    private static void borrowBook(Scanner scanner) {
        viewBooks();
        System.out.print("Enter book number to borrow: ");
        int bookNumber = scanner.nextInt();
        if (bookNumber > 0 && bookNumber <= books.size()) {
            Book book = books.get(bookNumber - 1);
            if (book.isAvailable()) {
                book.borrowBook();
                System.out.println("Book borrowed.");
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Invalid book number.");
        }
    }

    private static void returnBook(Scanner scanner) {
        viewBooks();
        System.out.print("Enter book number to return: ");
        int bookNumber = scanner.nextInt();
        if (bookNumber > 0 && bookNumber <= books.size()) {
            Book book = books.get(bookNumber - 1);
            if (!book.isAvailable()) {
                book.returnBook();
                System.out.println("Book returned.");
            } else {
                System.out.println("Book was not borrowed.");
            }
        } else {
            System.out.println("Invalid book number.");
        }
    }
}

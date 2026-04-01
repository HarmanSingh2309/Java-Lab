import java.util.ArrayList;

// Custom Exception for Invalid Book Data
class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message);
    }
}

// Book Class (NOT public)
class Book {

    private String title;
    private String author;
    private double price;
    private int stockCount;
    private String ISBN;

    // Default Constructor
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0.0;
        this.stockCount = 0;
        this.ISBN = "N/A";
    }

    // Parameterised Constructor
    public Book(String title, String author, double price,
                int stockCount, String ISBN) throws InvalidBookException {

        if (price < 0)
            throw new InvalidBookException("Price cannot be negative.");

        if (stockCount < 0)
            throw new InvalidBookException("Stock count cannot be negative.");

        if (ISBN == null || ISBN.length() < 5)
            throw new InvalidBookException("Invalid ISBN.");

        this.title = title;
        this.author = author;
        this.price = price;
        this.stockCount = stockCount;
        this.ISBN = ISBN;
    }

    // Copy Constructor
    public Book(Book b) {
        this.title = b.title;
        this.author = b.author;
        this.price = b.price;
        this.stockCount = b.stockCount;
        this.ISBN = b.ISBN;
    }

    // Method to display book details
    public void display() {
        System.out.println("Title      : " + title);
        System.out.println("Author     : " + author);
        System.out.println("Price      : " + price);
        System.out.println("StockCount : " + stockCount);
        System.out.println("ISBN       : " + ISBN);
        System.out.println("---------------------------");
    }
}

// Main Class (ONLY public class)
public class BookStoreDemo {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        try {
            // Using Parameterised Constructor
            Book b1 = new Book(
                    "Java Programming",
                    "Herbert Schildt",
                    550.0,
                    10,
                    "ISBN001"
            );

            Book b2 = new Book(
                    "Python for Data Science",
                    "Jake VanderPlas",
                    650.0,
                    5,
                    "ISBN002"
            );

            // Using Copy Constructor
            Book b3 = new Book(b1);

            // Adding to ArrayList
            bookList.add(b1);
            bookList.add(b2);
            bookList.add(b3);

            // Display all books
            System.out.println("Book Details:\n");
            for (Book b : bookList) {
                b.display();
            }

            // Creating book with invalid data (Exception Demo)
            Book b4 = new Book(
                    "Invalid Book",
                    "Test Author",
                    -200,   // Invalid price
                    3,
                    "ISBN003"
            );

            bookList.add(b4);

        } catch (InvalidBookException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Exception: " + e);
        }
    }
}
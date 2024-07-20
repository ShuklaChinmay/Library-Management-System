import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager;
        IssueManager issueManager;

        try {
            bookManager = new BookManager();
            issueManager = new IssueManager();

            while (true) {
                System.out.println("1. Add Book");
                System.out.println("2. List Books");
                System.out.println("3. Issue Book");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Name: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Enter Author Name: ");
                        String authorName = scanner.nextLine();
                        System.out.print("Enter Number Of Books: ");
                        int numberOfBooks = scanner.nextInt();
                        bookManager.addBook(bookName, authorName, numberOfBooks);
                        break;

                    case 2:
                        bookManager.listBooks();
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        int bookID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Person Name: ");
                        String personName = scanner.nextLine();
                        System.out.print("Enter Issue Date (YYYY-MM-DD): ");
                        Date issueDate = Date.valueOf(scanner.nextLine());
                        System.out.print("Enter Return Date (YYYY-MM-DD): ");
                        Date returnDate = Date.valueOf(scanner.nextLine());
                        issueManager.issueBook(bookID, personName, issueDate, returnDate);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

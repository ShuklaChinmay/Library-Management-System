import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManager {
    private Connection connection;

    public BookManager() throws SQLException {
        connection = DatabaseConnection.getConnection("LibraryDB");
    }

    public void addBook(String bookName, String authorName, int numberOfBooks) throws SQLException {
        String query = "INSERT INTO Books (BookName, AuthorName, NumberOfBooks) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bookName);
        preparedStatement.setString(2, authorName);
        preparedStatement.setInt(3, numberOfBooks);
        preparedStatement.executeUpdate();
    }

    public void listBooks() throws SQLException {
        String query = "SELECT * FROM Books";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Book ID: " + resultSet.getInt("BookID"));
            System.out.println("Book Name: " + resultSet.getString("BookName"));
            System.out.println("Author Name: " + resultSet.getString("AuthorName"));
            System.out.println("Number Of Books: " + resultSet.getInt("NumberOfBooks"));
            System.out.println();
        }
    }
}

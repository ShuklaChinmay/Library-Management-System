import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueManager {
    private Connection connection;

    public IssueManager() throws SQLException {
        connection = DatabaseConnection.getConnection("IssueDB");
    }

    public void issueBook(int bookID, String personName, Date issueDate, Date returnDate) throws SQLException {
        if (getIssuedBookCount(personName) >= 3) {
            System.out.println("The person has already issued 3 books.");
            return;
        }

        String query = "INSERT INTO BookIssues (BookID, PersonName, IssueDate, ReturnDate) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bookID);
        preparedStatement.setString(2, personName);
        preparedStatement.setDate(3, issueDate);
        preparedStatement.setDate(4, returnDate);
        preparedStatement.executeUpdate();
    }

    public int getIssuedBookCount(String personName) throws SQLException {
        String query = "SELECT COUNT(*) AS bookCount FROM BookIssues WHERE PersonName = ? AND ReturnDate IS NULL";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, personName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("bookCount");
        }
        return 0;
    }
}

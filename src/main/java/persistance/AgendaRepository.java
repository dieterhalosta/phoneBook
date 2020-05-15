package persistance;

import transfer.CreateEntryRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendaRepository {

    public void createEntry(CreateEntryRequest request) throws IOException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO agenda (first_name, last_name, number) VALUES (?, ?, ?)";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setInt(3, request.getNumber());

            preparedStatement.executeUpdate();
        }
    }
}

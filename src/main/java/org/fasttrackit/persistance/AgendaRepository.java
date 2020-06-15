package org.fasttrackit.persistance;

import org.fasttrackit.domanin.Agenda;
import org.fasttrackit.transfer.CreateEntryRequest;
import org.fasttrackit.transfer.DeleteEntryWithParamsRequest;
import org.fasttrackit.transfer.UpdateEntryRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createEntry(CreateEntryRequest request) throws IOException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO agenda (first_name, last_name, number, email) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setInt(3, request.getNumber());
            preparedStatement.setString(4, request.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    public void updateEntry(long id, UpdateEntryRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE agenda SET first_name=?, last_name=?, number=?, email=? WHERE id=? ";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setInt(3, request.getNumber());
            preparedStatement.setString(4, request.getEmail());
            preparedStatement.setLong(5, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteEntry(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM agenda WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

    }

    public void deleteEntryWithParams(DeleteEntryWithParamsRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM agenda WHERE firs_name=? ";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, request.getFirsName());
            preparedStatement.executeUpdate();
        }
    }

    public static List<Agenda> getAgenda() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, first_name, last_name, number, email FROM agenda";


        List<Agenda> entries = new ArrayList<>();

        try(Connection connection = DatabaseConfiguration.getConnection();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()){
                Agenda entry = new Agenda();

                entry.setId(resultSet.getLong("id"));
                entry.setFirstName(resultSet.getString("first_name"));
                entry.setLastName(resultSet.getString("last_name"));
                entry.setNumber(resultSet.getInt("number"));
                entry.setEmail(resultSet.getString("email"));

                entries.add(entry);
            }
        }

        return entries;
    }

}

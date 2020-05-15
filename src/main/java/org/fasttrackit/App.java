package org.fasttrackit;


import persistance.AgendaRepository;
import transfer.CreateEntryRequest;

import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException {
        CreateEntryRequest request = new CreateEntryRequest();
        request.setFirstName("TestFirstName");
        request.setLastName("TestLastName");
        request.setNumber(12333);


        AgendaRepository agendaRepository = new AgendaRepository();
        agendaRepository.createEntry(request);



    }
}

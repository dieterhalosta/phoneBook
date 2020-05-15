package org.fasttrackit;


import domanin.Agenda;
import persistance.AgendaRepository;
import transfer.CreateEntryRequest;
import transfer.DeleteEntryWithParamsRequest;
import transfer.UpdateEntryRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {
        //Create an Entry in agenda
        CreateEntryRequest request = new CreateEntryRequest();
        request.setFirstName("TestFirstName");
        request.setLastName("TestLastName");
        request.setNumber(12333);


        AgendaRepository agendaRepository = new AgendaRepository();
        agendaRepository.createEntry(request);


        //Update an Entry from the agenda
//        UpdateEntryRequest updateRequest = new UpdateEntryRequest();
//        updateRequest.setFirstName("TestFN");
//        updateRequest.setLastName("TestLN");
//        updateRequest.setNumber(98765);
//
//        agendaRepository.updateEntry(2, updateRequest);

        //Delete an Entry based only on  the ID
//        agendaRepository.deleteEntry(2);


        //Delete an Entry based on the firstName
//        DeleteEntryWithParamsRequest deleteEntry = new DeleteEntryWithParamsRequest();
//        deleteEntry.setFirsName("Dieter2");
//
//        agendaRepository.deleteEntryWithParams(deleteEntry);

        List<Agenda> entries = AgendaRepository.getAgenda();
        System.out.println(entries);

    }
}

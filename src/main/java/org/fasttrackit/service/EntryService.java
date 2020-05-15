package org.fasttrackit.service;

import org.fasttrackit.domanin.Agenda;
import org.fasttrackit.persistance.AgendaRepository;
import org.fasttrackit.transfer.CreateEntryRequest;
import org.fasttrackit.transfer.DeleteEntryWithParamsRequest;
import org.fasttrackit.transfer.UpdateEntryRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EntryService {

    private AgendaRepository agendaRepository = new AgendaRepository();

    public void createEntry(CreateEntryRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating entry: " + request);

        agendaRepository.createEntry(request);
    }

    public void updateEntry(long id, UpdateEntryRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating entry " + id + ": " + request);
        agendaRepository.updateEntry(id, request);
    }

    public void deleteEntry(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting entry " + id);
        agendaRepository.deleteEntry(id);
    }

    public void deleteEntryWithParam(DeleteEntryWithParamsRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting entry where first name is" + request);
        agendaRepository.deleteEntryWithParams(request);
    }

    public List<Agenda> getEntry() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving entries.");
        return agendaRepository.getAgenda();
    }
}

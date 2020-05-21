package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.EntryService;
import org.fasttrackit.transfer.CreateEntryRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/entries")
public class EntryServlet extends HttpServlet {

    private EntryService entryService = new EntryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateEntryRequest request = new ObjectMapper().readValue(req.getReader(), CreateEntryRequest.class);

        try {
            entryService.createEntry(request);
        } catch (SQLException | ClassNotFoundException e) {
           resp.sendError(500, "There was an error reaching the server. " + e.getMessage());
        }
    }
}

package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domanin.Agenda;
import org.fasttrackit.service.EntryService;
import org.fasttrackit.transfer.CreateEntryRequest;
import org.fasttrackit.transfer.UpdateEntryRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/entries")
public class EntryServlet extends HttpServlet {

    private EntryService entryService = new EntryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);
        CreateEntryRequest request = ObjectMapperConfiguration.OBJECT_MAPPER.readValue(req.getReader(), CreateEntryRequest.class);

        try {
            entryService.createEntry(request);
        } catch (SQLException | ClassNotFoundException e) {
           resp.sendError(500, "There was an error reaching the server. " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);

        final String id = req.getParameter("id");

        UpdateEntryRequest request = ObjectMapperConfiguration.OBJECT_MAPPER.readValue(req.getReader(), UpdateEntryRequest.class);

        try {
            entryService.updateEntry(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error reaching the server. " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);
        final String id = req.getParameter("id");

        try {
            entryService.deleteEntry(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error reaching the server. " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);
        try {
            List<Agenda> entries = entryService.getEntry();
            ObjectMapperConfiguration.OBJECT_MAPPER.writeValue(resp.getWriter(), entries);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "There was an error reaching the server. " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCorsHeaders(resp);
    }

    private void addCorsHeaders(HttpServletResponse resp){
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","POST, GET, PUT, DELETE");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
    }
}

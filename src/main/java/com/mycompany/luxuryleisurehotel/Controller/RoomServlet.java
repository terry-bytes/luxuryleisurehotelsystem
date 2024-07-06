package com.mycompany.luxuryleisurehotel.Controller;

import com.mycompany.luxuryleisurehotel.Models.Room;
import com.mycompany.luxuryleisurehotel.Services.RoomService;
import com.mycompany.luxuryleisurehotel.Services.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomServlet", urlPatterns = {"/RoomServlet"})
public class RoomServlet extends HttpServlet {

    private RoomService roomService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.roomService = new RoomServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomService.getAllRooms();

        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("allRooms.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }
}

package com.mycompany.luxuryleisurehotel.Controller;

import com.mycompany.luxuryleisurehotel.Models.Room;
import com.mycompany.luxuryleisurehotel.Models.Booking;
import com.mycompany.luxuryleisurehotel.Models.Payment;
import com.mycompany.luxuryleisurehotel.Services.RoomService;
import com.mycompany.luxuryleisurehotel.Services.RoomServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.BookingService;
import com.mycompany.luxuryleisurehotel.Services.BookingServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.PaymentService;
import com.mycompany.luxuryleisurehotel.Services.PaymentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    private RoomService roomService;
    private BookingService bookingService;
    private PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        this.roomService = new RoomServiceImpl();
        this.bookingService = new BookingServiceImpl();
        this.paymentService = new PaymentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "viewRooms":
                viewRooms(request, response);
                break;
            case "viewBookings":
                viewBookings(request, response);
                break;
            case "viewPayments":
                viewPayments(request, response);
                break;
            default:
                response.sendRedirect("adminDashboard.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "addRoom":
                addRoom(request, response);
                break;
            case "updateRoom":
                updateRoom(request, response);
                break;
            case "deleteRoom":
                deleteRoom(request, response);
                break;
            case "deleteBooking":
                deleteBooking(request, response);
                break;
            case "addBooking":
                addBooking(request, response);
                break;
            case "editBooking":
                editBooking(request, response);
                break;
            default:
                response.sendRedirect("adminDashboard.jsp");
                break;
        }

    }

    private void viewRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("viewRooms.jsp").forward(request, response);
    }

    private void addRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String roomType = request.getParameter("roomType");
        double rate = Double.parseDouble(request.getParameter("rate"));
        int maxOccupancy = Integer.parseInt(request.getParameter("maxOccupancy"));

        Room room = new Room();
        room.setRoomType(roomType);
        room.setRate(rate);
        room.setMaxOccupancy(maxOccupancy);

        roomService.addRoom(room);

        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("allRooms.jsp").forward(request, response);
    }

    private void updateRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        String roomType = request.getParameter("roomType");
        String rateStr = request.getParameter("rate");
        String maxOccupancyStr = request.getParameter("maxOccupancy");

        if (idStr == null || idStr.isEmpty()
                || roomType == null || roomType.isEmpty()
                || rateStr == null || rateStr.isEmpty()
                || maxOccupancyStr == null || maxOccupancyStr.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            forwardToAllRooms(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            double rate = Double.parseDouble(rateStr);
            int maxOccupancy = Integer.parseInt(maxOccupancyStr);

            Room updatedRoom = new Room(id, roomType, rate, maxOccupancy);
            boolean updateSuccess = roomService.updateRoom(updatedRoom);

            if (updateSuccess) {
                request.setAttribute("successMessage", "Room updated successfully.");
            } else {
                request.setAttribute("errorMessage", "Update failed. Room ID might be incorrect.");
            }
            forwardToAllRooms(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input: please enter valid numbers.");
            forwardToAllRooms(request, response);
        }
    }

    private void forwardToAllRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("allRooms.jsp").forward(request, response);
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        roomService.deleteRoom(id);

        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("allRooms.jsp").forward(request, response);
    }

    private void viewBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = bookingService.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("deleteBooking.jsp").forward(request, response);
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = bookingService.deleteBooking(id);

            if (isDeleted) {
                request.setAttribute("successMessage", "Booking deleted successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to delete booking. Please try again.");
            }

            List<Booking> allBookings = bookingService.getAllBookings();
            request.setAttribute("allBookings", allBookings);
            request.getRequestDispatcher("allBookings.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid booking ID. Please enter a valid number.");
            List<Booking> allBookings = bookingService.getAllBookings();
            request.setAttribute("allBookings", allBookings);
            request.getRequestDispatcher("allBookings.jsp").forward(request, response);
        }
    }

    private void viewPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = paymentService.getAllPayments();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("viewPayments.jsp").forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String status = request.getParameter("status");
        String diningPreference = request.getParameter("diningPreference");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Timestamp startTime = null;
        Timestamp endTime = null;
        try {
            startTime = Timestamp.valueOf(startTimeStr.replace("T", " ") + ":00");
            endTime = Timestamp.valueOf(endTimeStr.replace("T", " ") + ":00");
        } catch (IllegalArgumentException e) {

            request.setAttribute("errorMessage", "Invalid date format for startTime or endTime.");
            request.getRequestDispatcher("addBooking.jsp").forward(request, response);
            return;
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setRoomId(roomId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setStatus(status);
        booking.setDiningPreference(diningPreference);
        booking.setTotalAmount(totalAmount);

        boolean isAdded = bookingService.addBooking(booking);

        if (isAdded) {
            request.setAttribute("successMessage", "Booking added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add booking. Please try again.");
        }

        List<Booking> bookings = bookingService.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("deleteBooking.jsp").forward(request, response);
    }

    private void editBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            request.setAttribute("errorMessage", "Booking ID is required.");
            request.getRequestDispatcher("editBooking.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Booking booking = bookingService.getBookingById(id);

            if (booking == null) {
                request.setAttribute("errorMessage", "Booking not found with ID: " + id);
                request.getRequestDispatcher("editBooking.jsp").forward(request, response);
                return;
            }

            booking.setUserId(Integer.parseInt(request.getParameter("userId")));
            booking.setRoomId(Integer.parseInt(request.getParameter("roomId")));

            String startTimeStr = request.getParameter("startTime").replace("T", " ") + ":00";
            String endTimeStr = request.getParameter("endTime").replace("T", " ") + ":00";
            Timestamp startTime = Timestamp.valueOf(startTimeStr);
            Timestamp endTime = Timestamp.valueOf(endTimeStr);

            booking.setStartTime(startTime);
            booking.setEndTime(endTime);

            booking.setStatus(request.getParameter("status"));
            booking.setDiningPreference(request.getParameter("diningPreference"));

            boolean updateResult = bookingService.updateBooking(booking);

            if (updateResult) {
                request.setAttribute("successMessage", "Booking updated successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update booking.");
            }

            request.setAttribute("booking", booking);
            request.getRequestDispatcher("editBooking.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid booking ID format.");
            request.getRequestDispatcher("editBooking.jsp").forward(request, response);
        }
    }

}

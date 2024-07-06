package com.mycompany.luxuryleisurehotel.Controller;

import com.mycompany.luxuryleisurehotel.Models.Booking;
import com.mycompany.luxuryleisurehotel.Models.Room;
import com.mycompany.luxuryleisurehotel.Models.User;
import com.mycompany.luxuryleisurehotel.Services.BookingService;
import com.mycompany.luxuryleisurehotel.Services.BookingServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.RoomService;
import com.mycompany.luxuryleisurehotel.Services.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;
    private RoomService roomService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookingService = new BookingServiceImpl();
        this.roomService = new RoomServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");

        switch (action) {
            case "Get My Bookings":
                handleGetUserBookings(request, response);
                break;
            case "Get All Bookings":
                handleGetAllBookings(request, response);
                break;
            case "Get Available Rooms":
                handleGetAvailableRooms(request, response);
                break;
            case "Get Booking History":
                handleGetUserBookingHistory(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");

        switch (action) {
            case "Book Now":
                handleBookRoom(request, response);
                break;
            case "Cancel Booking":
                handleCancelBooking(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    private void handleBookRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String roomIdStr = request.getParameter("roomId");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String diningPreference = request.getParameter("diningPreference");

        Timestamp startTime = Timestamp.valueOf(LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        int roomId = Integer.parseInt(roomIdStr);

        boolean isRoomAvailable = roomService.isRoomAvailable(roomId, startTime, endTime);
        if (!isRoomAvailable) {
            request.setAttribute("error", "The room is already booked for this period.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        double totalAmount = calculateTotalAmount(roomId, startTime, endTime);

        boolean isBooked = bookingService.bookRoom(loggedInUser.getId(), roomId, startTime, endTime, "booked", diningPreference, totalAmount);

        if (isBooked) {
            HttpSession session = request.getSession();
            session.setAttribute("roomId", roomId);
            session.setAttribute("startTime", startTime);
            session.setAttribute("endTime", endTime);
            session.setAttribute("totalAmount", totalAmount);

            response.sendRedirect("checkout.jsp");
        } else {
            request.setAttribute("error", "Failed to book the room.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
    }

    private double calculateTotalAmount(int roomId, Timestamp startTime, Timestamp endTime) {
        double rate = getRoomRate(roomId);
        long durationMillis = endTime.getTime() - startTime.getTime();
        long durationHours = durationMillis / (60 * 60 * 1000);

        return rate * durationHours;
    }

    private double getRoomRate(int roomId) {
        Room room = roomService.getRoomById(roomId);
        if (room != null) {
            return room.getRate();
        } else {

            return 0.0;
        }
    }

    private void handleGetUserBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Booking> userBookings = bookingService.getUserBookings(loggedInUser.getId());

        request.setAttribute("userBookings", userBookings);
        request.getRequestDispatcher("userBookings.jsp").forward(request, response);
    }

    private void handleGetAllBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> allBookings = bookingService.getAllBookings();

        request.setAttribute("allBookings", allBookings);
        request.getRequestDispatcher("allBookings.jsp").forward(request, response);
    }

    private void handleGetAvailableRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> availableRooms = roomService.getAllAvailableRooms();
        request.setAttribute("availableRooms", availableRooms);
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    private void handleCancelBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String bookingIdStr = request.getParameter("bookingId");
        int bookingId = Integer.parseInt(bookingIdStr);

        boolean isCancelled = bookingService.cancelBooking(bookingId);

        if (isCancelled) {

            request.setAttribute("message", "Booking cancelled successfully.");
        } else {
            request.setAttribute("error", "Failed to cancel the booking.");
        }

        handleGetUserBookings(request, response);
    }

    private void handleGetUserBookingHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Booking> userBookingHistory = bookingService.getUserBookingHistory(loggedInUser.getId());
        request.setAttribute("userBookingHistory", userBookingHistory);

        request.getRequestDispatcher("userBookingHistory.jsp").forward(request, response);
    }

}

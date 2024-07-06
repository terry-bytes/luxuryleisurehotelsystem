package com.mycompany.luxuryleisurehotel.Controller;

import com.mycompany.luxuryleisurehotel.Models.Email;
import com.mycompany.luxuryleisurehotel.Models.User;
import com.mycompany.luxuryleisurehotel.Services.BookingService;
import com.mycompany.luxuryleisurehotel.Services.BookingServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.EmailService;
import com.mycompany.luxuryleisurehotel.Services.EmailServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.PaymentService;
import com.mycompany.luxuryleisurehotel.Services.PaymentServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.RoomService;
import com.mycompany.luxuryleisurehotel.Services.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PaymentConfirmationServlet", urlPatterns = {"/PaymentConfirmationServlet"})
public class PaymentConfirmationServlet extends HttpServlet {

    private BookingService bookingService;
    private RoomService roomService;
    private EmailService emailService;
    private PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookingService = new BookingServiceImpl();
        this.roomService = new RoomServiceImpl();
        this.emailService = new EmailServiceImpl();
        this.paymentService = new PaymentServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String roomIdStr = request.getParameter("roomId");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String totalAmountStr = request.getParameter("totalAmount");
        int roomId = Integer.parseInt(roomIdStr);
        double totalAmount = Double.parseDouble(totalAmountStr);
        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = loggedInUser.getId();
        String userEmail = loggedInUser.getEmail();

        boolean paymentRecorded = paymentService.recordPayment(roomId, totalAmount, "Paid");

        if (paymentRecorded) {
            String subject = "Invoice for Room Booking";
            String content = "Payment Confirmed for Room ID: " + roomId + "<br>"
                    + "Start Time: " + startTime + "<br>"
                    + "End Time: " + endTime + "<br>"
                    + "Total Amount: R" + totalAmount;

            Email invoiceEmail = new Email();
            invoiceEmail.setSender("ramovhatp@gmail.com");
            invoiceEmail.setPassword("celw juwu rdis zplg");
            invoiceEmail.setReceiver(userEmail);
            invoiceEmail.setSubject(subject);
            invoiceEmail.setMessage(content);

            emailService.sendMail(invoiceEmail);

            String confirmationMessage = "Payment Confirmed for Room ID: " + roomId
                    + "<br>Start Time: " + startTime
                    + "<br>End Time: " + endTime
                    + "<br>Total Amount: R" + totalAmount;

            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Payment Confirmation</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-image: url('images/Forbes Travel.jpg'); background-size: cover; background-position: center; background-repeat: no-repeat; height: 100vh; display: flex; align-items: center; justify-content: center; margin: 0; }");
            out.println(".confirmation-container { max-width: 600px; margin: 0 auto; background-color: rgba(255, 255, 255, 0.8); padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("h2 { font-size: 2rem; text-align: center; color: #007bff; margin-bottom: 20px; }");
            out.println(".confirmation-message { font-size: 1.2rem; line-height: 1.6; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='confirmation-container'>");
            out.println("<h2>Payment Confirmed</h2>");
            out.println("<div class='confirmation-message'>" + confirmationMessage + "</div>");
            out.println("<script>");
            out.println("setTimeout(function() { window.location.href = 'booking.jsp'; }, 5000);");
            out.println("</script>");
            out.println("</div>");
            out.println("</body></html>");

        } else {
            response.getWriter().println("Failed to record payment for Room ID: " + roomId);
        }
    }
}

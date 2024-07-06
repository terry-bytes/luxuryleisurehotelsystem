package com.mycompany.luxuryleisurehotel.Controller;

import com.mycompany.luxuryleisurehotel.DAOIMPL.BookingDaoImpl;
import com.mycompany.luxuryleisurehotel.DAOIMPL.UserDaoImpl;
import com.mycompany.luxuryleisurehotel.Models.Email;
import com.mycompany.luxuryleisurehotel.Models.User;
import com.mycompany.luxuryleisurehotel.Services.EmailServiceImpl;
import com.mycompany.luxuryleisurehotel.Services.UserService;
import com.mycompany.luxuryleisurehotel.Services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private final UserService userService;

    public UserServlet() {
        try {
            this.userService = new UserServiceImpl(new UserDaoImpl(), new BookingDaoImpl(), new EmailServiceImpl());
        } catch (Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, "Error initializing UserService", e);
            throw new RuntimeException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("submit");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return;
        }

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "verifyOTP":
                verifyOTP(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "initiateReset":
                initiatePasswordReset(request, response);
                break;
            case "verifyResetOTP":
                verifyResetOTP(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
                break;
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        if (!isValidEmail(email)) {
            request.setAttribute("error", "Invalid email address.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("surname", surname);
        session.setAttribute("email", email);
        session.setAttribute("password", password);

        generateAndSendOTP(request, response);
        request.setAttribute("email", email);
        request.getRequestDispatcher("verifyOtp.jsp").forward(request, response);
    }

    private void generateAndSendOTP(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email").trim();
        String otp = generateOTP();

        Email emailDetails = new Email("ramovhatp@gmail.com", "celw juwu rdis zplg");
        emailDetails.setReceiver(email);
        emailDetails.setSubject("Your OTP Code");
        emailDetails.setMessage("Your OTP code is: " + otp + "\nRegards,\nLuxury Leisure Hotel Team");

        EmailServiceImpl emailService = new EmailServiceImpl(emailDetails);
        emailService.sendMail(emailDetails);

        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);
    }

    private void verifyOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String otpEntered = request.getParameter("otp");
        String otpSession = (String) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");

        if (otpSession != null && otpSession.equals(otpEntered)) {

            try {
                String name = (String) session.getAttribute("name");
                String surname = (String) session.getAttribute("surname");
                String password = (String) session.getAttribute("password");

                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPassword(password);

                String result = userService.register(user);
                if ("registered successfully".equals(result)) {
                    request.setAttribute("message", "Registration successful! You can now log in.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "User already exists or registration failed.");
                    request.getRequestDispatcher("verifyOtp.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "An error occurred during registration. Please try again.");
                request.getRequestDispatcher("verifyOtp.jsp").forward(request, response);
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, "Error during user registration", e);
            }
        } else {
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("verifyOtp.jsp").forward(request, response);
        }
    }

    private void initiatePasswordReset(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email").trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            request.setAttribute("error", "Invalid email address.");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String otp = userService.initiatePasswordReset(email);
        session.setAttribute("resetOtp", otp);
        session.setAttribute("resetEmail", email);
        request.setAttribute("message", "An OTP has been sent to your email. Please enter it below to reset your password.");
        request.getRequestDispatcher("verifyResetOtp.jsp").forward(request, response);
    }

    private void verifyResetOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String resetOtpSession = (String) session.getAttribute("resetOtp");
        String resetOtpEntered = request.getParameter("otp");
        String resetEmail = (String) session.getAttribute("resetEmail");
        String newPassword = request.getParameter("newPassword");

        if (resetOtpSession != null && resetOtpSession.equals(resetOtpEntered)) {
            userService.resetPassword(resetEmail, newPassword);
            request.setAttribute("message", "Password has been reset successfully. You can now log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("verifyResetOtp.jsp").forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    @Override
    public String getServletInfo() {
        return "User Servlet";
    }

    private String generateOTP() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        try {
            User loggedInUser = userService.login(userEmail, userPassword);

            if (loggedInUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", loggedInUser);
                if (loggedInUser.isAdmin()) {
                    response.sendRedirect(request.getContextPath() + "/adminDashboard?action=AdminHome");
                } else {
                    response.sendRedirect(request.getContextPath() + "/booking.jsp");
                }

            } else {
                request.setAttribute("error", "Incorrect email or password. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, "Login error", ex);
        }
    }
}

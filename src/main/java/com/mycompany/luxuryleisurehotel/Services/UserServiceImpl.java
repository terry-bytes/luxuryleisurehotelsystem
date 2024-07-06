package com.mycompany.luxuryleisurehotel.Services;

import com.mycompany.luxuryleisurehotel.DAO.BookingDao;
import com.mycompany.luxuryleisurehotel.DAO.UserDao;
import com.mycompany.luxuryleisurehotel.Models.Email;
import com.mycompany.luxuryleisurehotel.Models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.login.LoginException;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BookingDao bookingDao;
    private EmailService emailService;

    public UserServiceImpl(UserDao userDao, BookingDao bookingDao, EmailService emailService) {
        this.userDao = userDao;
        this.bookingDao = bookingDao;
        this.emailService = emailService;
    }

    @Override
    public String register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        String str = userDao.addUser(user) ? "registered successfully" : "failed to register";
        if (str.equals("registered successfully")) {
            sendRegistrationEmail(user);
            notifyAdminOnRegistration(user);
        }
        return str;
    }

    @Override
    public User login(String email, String password) throws LoginException {
        User user = userDao.login(email, password);
        String hash = user.getPassword();

        if (!BCrypt.checkpw(password, hash)) {
            throw new LoginException("Password incorrect");
        }
        return user;
    }

    @Override
    public void sendRegistrationEmail(User user) {
        String subject = "Registration Confirmation";
        String message = "Dear " + user.getName() + ",\n\n"
                + "Thank you for registering with us.\n\n"
                + "Best regards,\n"
                + "Luxury Leisure Hotel Team";

        Email email = new Email("ramovhatp@gmail.com", user.getEmail(), message, "celw juwu rdis zplg", subject);
        emailService.sendMail(email);
    }

    @Override
    public void notifyAdminOnRegistration(User user) {
        String adminEmail = "ramovhatp@gmail.com";
        String subject = "New User Registration";
        String message = "A new user has registered:\n\n"
                + "Name: " + user.getName() + " " + user.getSurname() + "\n"
                + "Email: " + user.getEmail();

        Email email = new Email("ramovhatp@gmail.com", adminEmail, message, "celw juwu rdis zplg", subject);
        emailService.sendMail(email);
    }

    @Override
    public String initiatePasswordReset(String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            return "No user found with that email.";
        }

        String otp = generateOTP();
        emailService.sendPasswordResetMail(email, otp);

        return otp;
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        userDao.updatePassword(email, hashedPassword);
    }

    private String generateOTP() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

}

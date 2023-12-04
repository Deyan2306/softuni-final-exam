package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;

import javax.naming.Context;

public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.mailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendOtpEmail(String recipientEmail, String otp) {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        try {
//            helper.setTo(recipientEmail);
//            helper.setSubject("OTP Verification Code");
//
//            // Prepare the Thymeleaf context with variables
//            Context context = new Context();
//            context.setVariable("otp", otp);
//
//            // Process the template and set the content of the email
//            String htmlContent = templateEngine.process("otp_email_template", context);
//            helper.setText(htmlContent, true);
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace(); // Handle exception properly
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

    }
}

package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String movieappEmail;

    @Autowired
    public EmailServiceImpl(
            TemplateEngine templateEngine,
            JavaMailSender javaMailSender,
            @Value("${mail.movieapp}") String movieappEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.movieappEmail = movieappEmail;
    }

    @Override
    public void sendRegistrationEmail(String userEmail, String userName, String activationCode) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {

            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(movieappEmail);
            mimeMessageHelper.setReplyTo(movieappEmail);
            mimeMessageHelper.setSubject("MovieAPP - Account Activation");
            mimeMessageHelper.setText(generateRegistrationEmailBody(userName, activationCode), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateRegistrationEmailBody(String userName, String activationCode) {

        Context context = new Context();
        context.setVariable("username", userName);
        context.setVariable("activation_code", activationCode);

        return templateEngine.process("email/registration-email", context);
    }

}

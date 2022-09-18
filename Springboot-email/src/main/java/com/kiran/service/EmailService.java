package com.kiran.service;

import com.kiran.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(request.getToEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getMessageBody());
        javaMailSender.send(message);
        return "email sent successfully to: " + request.getToEmail();
    }

    public String sendEmailWithAttachment(EmailRequest request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(sender);
        helper.setTo(request.getToEmails());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMessageBody());

        FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        javaMailSender.send(mimeMessage);
        return "mail sent successfully with attachment " + file.getFilename();
    }

}

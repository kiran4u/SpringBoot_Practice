package com.kiran.controller;

import com.kiran.dto.EmailRequest;
import com.kiran.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailRequest request) {
        return emailService.sendSimpleEmail(request);
    }

    @PostMapping("/sendAttachment")
    public String sendAttachment(@RequestBody EmailRequest request) throws MessagingException {
        return emailService.sendEmailWithAttachment(request);
    }


}

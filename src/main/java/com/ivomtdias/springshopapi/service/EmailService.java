package com.ivomtdias.springshopapi.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String htmlBody) throws MessagingException;
}

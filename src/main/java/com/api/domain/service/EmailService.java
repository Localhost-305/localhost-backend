package com.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envia um e-mail para o destinatário especificado.
     *
     * @param recipient O endereço de e-mail do destinatário.
     * @param subject   O assunto do e-mail.
     * @param message   O corpo da mensagem do e-mail.
     */
    public void sendEmail(String recipient, String subject, String message) {
        if (recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("O destinatário não pode ser nulo ou vazio.");
        }

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("laroyprado@gmail.com"); 
        email.setTo(recipient); 
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
        System.out.println("E-mail enviado com sucesso para: " + recipient);
    }
}

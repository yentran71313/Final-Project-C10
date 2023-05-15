//package com.cg.service.mail;
//
//import lombok.AllArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//@AllArgsConstructor
//public class MailService {
//    private final JavaMailSender javaMailSender;
//
//    @Async
//    CompletableFuture<Boolean> sendCodeActiveAccount (String email, String title) throws InterruptedException{
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("");
//        message.setTo();
//        message.setText("");
//        try {
//            this.javaMailSender.send(message);
//            return CompletableFuture.completedFuture(Boolean.TRUE);
//        } catch (Exception e) {
//            return CompletableFuture.completedFuture(Boolean.FALSE);
//        }
//    }
//}

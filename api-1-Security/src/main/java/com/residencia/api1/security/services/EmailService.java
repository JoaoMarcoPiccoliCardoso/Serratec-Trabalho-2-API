package com.residencia.api1.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    public JavaMailSender emailSender;
    
    //Acessar as propriedades do emailSender (@Value("${nome da propriedade}"))
    
    @Value("${spring.mail.host}")
    private String mailHost;
    
    @Value("${spring.mail.port}")
    private String mailPort;
    
    @Value("${spring.mail.username}")
    private String userName;
    
    @Value("${spring.mail.password}")
    private String password;
    
    @Value("${mail.from}")
    private String mailFrom;
    
    public EmailService(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }
    
    //Enviar o email - SIMPLES
    //    O avançado ver por fora (necessário)
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        var mailMessage = new SimpleMailMessage();
        
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(assunto);
        mailMessage.setText(mensagem);
        //quem envia
        mailMessage.setFrom(mailFrom);
        
        try {
            emailSender.send(mailMessage);
        }catch(Exception ex) {
            System.out.println("Ocorreu um erro ao tentar enviar o e-mail: " + ex.getMessage());
        }
    }
}
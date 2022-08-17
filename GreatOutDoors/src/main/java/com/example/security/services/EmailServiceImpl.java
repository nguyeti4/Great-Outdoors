package com.example.security.services;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.payload.request.EmailDetails;


@Service
public class EmailServiceImpl implements EmailService{
	private static final Logger logger=LogManager.getLogger(EmailService.class);
	
	@Autowired private JavaMailSender javaMailSender;
	
	@Value("$(spring.mail.username)") private String sender;
	
	@Override
	public String sendSimpleMail(EmailDetails details) {
		try {
			/*SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo("timothynguyen2000@yahoo.com");
			mailMessage.setText(details.getQuery());
			mailMessage.setSubject("Client Message");*/
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setSubject("Great Outdoors: Client Message");
			helper.setFrom(sender);
			helper.setTo("timothynguyen2000@yahoo.com");
			helper.setText(
					  "<b>Sender Name</b>: <i>"+details.getFirstName()+" "+details.getLastName()+"</i><br>"
					+ "<b>Sender Email</b>: <i>"+details.getEmail()+"</i><br>"
					+ "<b>Sender Message</b>: <br><p>"+details.getQuery()+"</p>",true);
			//send the mail
			javaMailSender.send(message);
			logger.info("Sent simple email");
			return "Mail sent Successfully...";
		}
		catch(Exception e) {
			logger.error("Error trying to send email: "+e);
			return "Error while sending email: " + e.getMessage();
		}
	}

}

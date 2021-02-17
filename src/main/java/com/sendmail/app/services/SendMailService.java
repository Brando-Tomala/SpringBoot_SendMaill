package com.sendmail.app.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sendmail.app.model.FormatMail;

@Service
public class SendMailService {

	@Value(value = "${spring.mail.username}")
	private String fromMail;
	

	@Autowired
	private JavaMailSender mailSender;


	public String sendSimpleMail(FormatMail mail) {
		String response="Ok";
		try {
			SimpleMailMessage mailMessage= new SimpleMailMessage();
			mailMessage.setFrom(fromMail);
			mailMessage.setTo(mail.getToMail());
			mailMessage.setSubject(mail.getSubject());
			mailMessage.setText(mail.getBody());

			mailSender.send(mailMessage);

		}catch (Exception e) {
			response = "Error";
		}
		return response;
	}


	public String sendAttachmentMail(FormatMail mail) {
		String resp="Ok";
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper
			= new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(fromMail);
			mimeMessageHelper.setTo(mail.getToMail());
			mimeMessageHelper.setText(mail.getBody());
			mimeMessageHelper.setSubject(mail.getSubject());


			FileSystemResource fileSystem
			= new FileSystemResource(new File("FILEPATH"));

			mimeMessageHelper.addAttachment(fileSystem.getFilename(),
					fileSystem);

			mailSender.send(mimeMessage);
			System.out.println("Mail Send...");

		}catch (Exception e) {
			resp = "Error";
		}
		return resp;
	}


}

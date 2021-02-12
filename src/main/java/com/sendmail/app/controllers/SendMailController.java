package com.sendmail.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sendmail.app.model.FormatMail;
import com.sendmail.app.services.SendMailService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("sendmail")
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMail(@RequestBody FormatMail mail) {
		return ResponseEntity.status(HttpStatus.OK).body(sendMailService.sendSimpleMail(mail));
	}
	
	@PostMapping(path = "attachment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMailAttachment(@RequestBody FormatMail mail) {
		return ResponseEntity.status(HttpStatus.OK).body(sendMailService.sendAttachmentMail(mail));
	}
	

}

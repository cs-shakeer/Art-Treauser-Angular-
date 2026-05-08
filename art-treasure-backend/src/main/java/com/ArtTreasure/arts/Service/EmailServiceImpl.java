package com.ArtTreasure.arts.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void registerMsg(String reciverEmail,String reciverName) {
		
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Welcome "+reciverName+" thank you for joining 'ArtTreasure'...😍😍😍");

        mailSender.send(message);

	}

	@Override
	public void followingsMsg(String reciverEmail, String senderName, String reciverName) {
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Hi "+reciverName+" you having new follower ☺, "+senderName+" followes you...🎉🎉🎉");
        mailSender.send(message);
		
	}

	@Override
	public void pinsMsg(String reciverEmail, String senderName, String reciverName) {
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Hi "+reciverName+" '"+senderName+"' added new new pin...📍🖼🤩,"+" it's soo nice please see that 😊😊😊");
        mailSender.send(message);
		
	}

	@Override
	public void userDeleteMsg(String reciverEmail, String reciverName) {
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Hi "+reciverName+" sorry for saying this your account has deleted...😐😐😐");

        mailSender.send(message);
	}

	@Override
	public void userBoardDeleteMsg(String reciverEmail, String reciverName, String boardName) {
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Hi "+reciverName+" sorry for saying this your "+boardName+" board has deleted...😐😐😐");

        mailSender.send(message);
		
	}

	@Override
	public void removeFollowerMsg(String reciverEmail, String senderName, String reciverName) {
		// Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reciverEmail);
        message.setSubject("ArtTreasue");
        message.setText("Hi "+reciverName+" sorry for saying this, "+senderName+" has removed in followers list...🙂🙂🙂");
        mailSender.send(message);
		
	}

//	@Override
//	public void sendOTP(String receiverEmail, String receiverName, Long otp) {
//	    SimpleMailMessage message = new SimpleMailMessage();
//	    message.setTo(receiverEmail);
//	    message.setSubject("ArtTreasure - Password Reset OTP");
//	    message.setText("Hi " + receiverName + ",\n\nYour OTP for password reset is: " + otp 
//	        + "\n\nIf you didn't request this, please ignore.");
//	    mailSender.send(message);
//	}
	
	@Override
	public void sendOTP(String receiverEmail, Long otp) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(receiverEmail);
	    message.setSubject("ArtTreasure - Password Reset OTP");
	    message.setText("Hi ,\n\nYour OTP for password reset is: " + otp 
	        + "\n\nIf you didn't request this, please ignore.");
	    mailSender.send(message);
	}


}

package com.ArtTreasure.arts.Service;

public interface EmailService {
	
	public void registerMsg(String reciverEmail,String reciverName);
	
	public void followingsMsg(String reciverEmail, String senderName, String reciverName);
	
	public void removeFollowerMsg(String reciverEmail, String senderName, String reciverName);
	
	public void pinsMsg(String reciverEmail, String senderName, String reciverName);
	
	public void userDeleteMsg(String reciverEmail,String reciverName);
	
	public void userBoardDeleteMsg(String reciverEmail,String reciverName,String boardName);
	
//	public void sendOTP(String reciverEmail,String reciverName,Long otp);
	
	public void sendOTP(String reciverEmail,Long otp);
	
	

}

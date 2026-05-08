package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.UserEntity;

public interface UserService {
	
	public String registerUser(UserEntity user);
	
	public UserEntity loginUser(UserEntity user);
	
	public UserEntity getUserById(Long userId);
	
	public UserEntity getUserByUserName(String userName);
	
	public UserEntity getUserByEmail(String email);
	
	public List<UserEntity> getAllUsers();
	
	public UserEntity updateUser(UserEntity user);
	
	public String forgotPasswordSendOTP(String email);
	
	public String verifyOTP(String email, Long otp);
	
	public UserEntity updatePasswordByNewPassword(String userName,String newPassword);
	
	public String deleteUserByUserId(Long userId);
	

}

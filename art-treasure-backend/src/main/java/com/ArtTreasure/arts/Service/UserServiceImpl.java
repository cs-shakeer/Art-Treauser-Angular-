package com.ArtTreasure.arts.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserEntity getUserById(Long userId) {
		if (userId != null) {
			return userRepo.findById(userId).orElse(null);
		}
		return null;
	}

	@Override
	public UserEntity getUserByUserName(String userName) {
		if (userName != null) {
			return userRepo.findByUserName(userName).orElse(null); // Optional handled safely
		}
		return null;
	}

	@Override
	public String registerUser(UserEntity user) {
		if (user != null) {
			String userName = user.getUserName();
			UserEntity existUser = getUserByUserName(userName);
			if (existUser == null) {
				user.setProfessional(false);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
				if (userRepo.save(user) != null) {
					emailService.registerMsg(user.getEmail(), userName);
					return "success";
				} else {
					return "failed";
				}
			} else {
				return "exist";
			}
		}
		return null;
	}

	@Override
	public UserEntity loginUser(UserEntity user) {
		if (user != null) {
			String userName = user.getUserName();
			UserEntity existUser = getUserByUserName(userName);
			if (existUser != null && passwordEncoder.matches(user.getPassword(), existUser.getPassword())) {
				return existUser;
			}
		}
		return null;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		if (email != null) {
			return userRepo.findByEmail(email).orElse(null); // Optional handled safely
		}
		return null;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepo.findAll(); // No null check needed
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		if (user != null && user.getUserId() != null) {
			UserEntity existUser = getUserById(user.getUserId());
			if (existUser != null) {

				existUser.setUserName(user.getUserName() != null ? user.getUserName() : existUser.getUserName());
				existUser.setFullName(user.getFullName() != null ? user.getFullName() : existUser.getFullName());
				existUser.setEmail(user.getEmail() != null ? user.getEmail() : existUser.getEmail());
				existUser.setMobile(user.getMobile() != null ? user.getMobile() : existUser.getMobile());
				existUser
						.setProfileImg(user.getProfileImg() != null ? user.getProfileImg() : existUser.getProfileImg());
                if (user.getPassword() != null && !user.getPassword().isEmpty() && !passwordEncoder.matches(user.getPassword(), existUser.getPassword())) {
                    existUser.setPassword(passwordEncoder.encode(user.getPassword()));
                }
				existUser.setProfessional(user.isProfessional());

				UserEntity updatedUser = userRepo.save(existUser);
				if (updatedUser != null) {
					return updatedUser;
				} else {
					return null;
				}

			}
		}
		return null;
	}

	@Override
	public String deleteUserByUserId(Long userId) {
		if (userId != null) {
			UserEntity existUser = getUserById(userId);
			if (existUser != null) {
				userRepo.deleteById(userId);
				emailService.userDeleteMsg(existUser.getEmail(), existUser.getUserName());
				return "success";
			} else {
				return "!exist";
			}
		} else {
			return null;
		}

	}

	// Store OTPs temporarily, e.g., in a map for demo (Use DB or cache in
	// production)
	private Map<String, Long> otpStore = new ConcurrentHashMap<>();

	@Override
	public String forgotPasswordSendOTP(String email) {
		if (email != null) {
//	        UserEntity existUser = userRepo.findByEmail(email).orElse(null);
//	        if (existUser != null) {
//	            // Generate a 6-digit random OTP
//	            Long otp = (long) (100000 + Math.random() * 900000);
//
//	            // Store OTP for email (replace with DB for production)
//	            otpStore.put(email, otp);
//
//	            // Send OTP by email
//	            emailService.sendOTP(email, existUser.getUserName(), otp);
//	            return "OTP sent to your registered email.";
//	        } else {
//	            return "User not found.";
//	        }

			// Generate a 6-digit random OTP
			Long otp = (long) (100000 + Math.random() * 900000);

			// Store OTP for email (replace with DB for production)
			otpStore.put(email, otp);

			// Send OTP by email
			emailService.sendOTP(email, otp);
			return "OTP sent to your registered email.";
		}
		return "Invalid email.";
	}

	@Override
	public String verifyOTP(String email, Long otp) {
		if (email != null && otp != null) {
			Long storedOtp = otpStore.get(email);
			if (storedOtp != null && storedOtp.equals(otp)) {
				otpStore.remove(email); // Remove OTP after successful verification
				return "OTP verified!";
			} else {
				return "Invalid OTP.";
			}
		}
		return "Invalid input.";
	}

	@Override
	public UserEntity updatePasswordByNewPassword(String userName, String newPassword) {
		if (userName != null && newPassword != null) {
			UserEntity existUser = getUserByUserName(userName);

			if (existUser != null) {
				existUser.setPassword(passwordEncoder.encode(newPassword));
				UserEntity updatedUser = userRepo.save(existUser);
				return updatedUser != null ? updatedUser : null;
			} else {
				return null;
			}
		}
		return null;
	}

}

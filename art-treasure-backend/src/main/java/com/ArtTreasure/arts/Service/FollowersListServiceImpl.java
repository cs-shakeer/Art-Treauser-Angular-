package com.ArtTreasure.arts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.FollowersListEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.FollowersRepository;
@Service
public class FollowersListServiceImpl implements FollowersListService{
	
	@Autowired
	private FollowersRepository followersRepo;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public List<FollowersListEntity> getUserFollowersListByUserId(Long userId) {
		if(userId!=null) {
			List<FollowersListEntity> followers=followersRepo.findByUser_UserId(userId);
			if(followers!=null) {
				return followers;
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String removeFollowerById(Long userId, Long followerId) {
	    if (userId != null && followerId != null) {
	        UserEntity existUser = userServ.getUserById(userId);
	        UserEntity existFollower = userServ.getUserById(followerId);

	        if (existUser != null && existFollower != null) {
	            List<FollowersListEntity> followers = getUserFollowersListByUserId(userId);

	            FollowersListEntity matchedFollower = followers.stream()
	                .filter(f -> f.getFollower().getUserId().equals(followerId)
	                          && f.getUser().getUserId().equals(userId))
	                .findFirst()
	                .orElse(null);

	            if (matchedFollower != null) {
	                followersRepo.delete(matchedFollower);
	                emailService.removeFollowerMsg(existFollower.getEmail(), existUser.getUserName(), existFollower.getUserName());
					notificationService.setRemoveFollowerNotification(existUser, existFollower);
	                return "success";
	            } else {
	                return "!exist";
	            }
	        }
	    }
	    return null;
	}


	@Override
	public FollowersListEntity getFollowerById(Long followerId) {
		if(followerId!=null) {
			UserEntity existUser = userServ.getUserById(followerId);
			if(existUser!=null) {
				return followersRepo.findById(followerId).orElse(null);
			}else {
				return null;
			}
		}
		return null;
	}

}

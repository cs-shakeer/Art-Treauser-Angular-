package com.ArtTreasure.arts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.FollowersListEntity;
import com.ArtTreasure.arts.entity.FollowsListEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.FollowersRepository;
import com.ArtTreasure.arts.repository.FollowsRepository;

@Service
public class FollowsListServiceImpl implements FollowsListService {

	@Autowired
	private FollowsRepository followsRepo;

	@Autowired
	private FollowersRepository followersRepo;

	@Autowired
	private UserService userServ;

	@Autowired
	private EmailService emailService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public List<FollowsListEntity> getUserFollowersListByUserId(Long userId) {
		if (userId != null) {
			List<FollowsListEntity> follows = followsRepo.findByUser_UserId(userId);
			if (follows != null) {
				return follows;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String setFollowing(FollowsListEntity follow) {
		if (follow.getUser().getUserId() != null && follow.getFollows().getUserId() != null) {
			UserEntity user = userServ.getUserById(follow.getUser().getUserId());
			UserEntity follows = userServ.getUserById(follow.getFollows().getUserId());

			if (user != null && follows != null) {
				FollowersListEntity follower = new FollowersListEntity();
				follower.setUser(follows);
				follower.setFollower(user);
				if (followsRepo.save(follow) != null && followersRepo.save(follower) != null) {
					emailService.followingsMsg(follows.getEmail(), user.getUserName(), follows.getUserName());
					notificationService.setFollowingNotification(user, follows);
					return "success";
				} else {
					return "failed";
				}
			} else {
				return null;
			}

		}
		return null;
	}

}

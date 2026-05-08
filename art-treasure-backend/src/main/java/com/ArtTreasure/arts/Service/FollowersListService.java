package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.FollowersListEntity;



public interface FollowersListService {
	public List<FollowersListEntity> getUserFollowersListByUserId(Long userId);
	
	public String removeFollowerById(Long userId,Long followerId);
	
	public FollowersListEntity getFollowerById(Long followerId);
	

}

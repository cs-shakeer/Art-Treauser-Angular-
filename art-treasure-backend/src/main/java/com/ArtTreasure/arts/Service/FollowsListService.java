package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.FollowsListEntity;

public interface FollowsListService {
	
	public String setFollowing(FollowsListEntity follow);
	
	public List<FollowsListEntity> getUserFollowersListByUserId(Long userId);

}

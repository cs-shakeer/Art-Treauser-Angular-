package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.NotificationEntity;
import com.ArtTreasure.arts.entity.PinEntity;
import com.ArtTreasure.arts.entity.UserEntity;

public interface NotificationService {
	public List<NotificationEntity> getUserNotificationByUserId(Long userId);
	
	public NotificationEntity getNotificationById(Long nId);
	
	public NotificationEntity setFollowingNotification(UserEntity sender, UserEntity reciver );
	
	public NotificationEntity setPinNotification(UserEntity sender, PinEntity pin, UserEntity reciver );
	
	public NotificationEntity setRemoveFollowerNotification(UserEntity sender, UserEntity reciver );
	
	public String setIsReadedByNotificationId(Long nId);
}

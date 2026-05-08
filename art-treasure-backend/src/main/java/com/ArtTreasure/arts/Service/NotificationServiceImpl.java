package com.ArtTreasure.arts.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.NotificationEntity;
import com.ArtTreasure.arts.entity.PinEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.NotificationRepository;
@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Override
	public NotificationEntity getNotificationById(Long nId) {
		if(nId != null) {
		return notificationRepo.findById(nId).orElse(null);
		}else {
			return null;
		}
	}

	@Override
	public List<NotificationEntity> getUserNotificationByUserId(Long userId) {
		if(userId!=null) {
			List<NotificationEntity> notifications=notificationRepo.findByReciver_UserId(userId);
			if(notifications!=null) {
				return notifications;
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public NotificationEntity setFollowingNotification(UserEntity sender, UserEntity reciver) {
	    if(sender != null && reciver != null) {
	        NotificationEntity notification = new NotificationEntity();
	        notification.setNotificationType("following");
	        notification.setMessage(sender.getUserName() + " starts following you...🎉🎉");
	        notification.setCreatedAt(LocalDateTime.now()); // Set current timestamp
	        notification.setSender(sender); // Set who sent the notification
	        notification.setReciver(reciver); // Set who receives the notification
	        notification.setRead(false); 
	        
	        NotificationEntity addedNotification =notificationRepo.save(notification); 

	        if(addedNotification!=null) {
	        	
	        	return addedNotification;
	        }else {
	        	return null;
	        }
	        
	    }
	    return null;
	}

	@Override
	public NotificationEntity setPinNotification(UserEntity sender, PinEntity pin, UserEntity reciver) {
		if(sender != null && reciver != null && pin!=null) {
	        NotificationEntity notification = new NotificationEntity();
	        notification.setNotificationType("pin");
	        if(pin.getPinType().equals("image")) {
	        	notification.setPinType("image");
	        	notification.setPin(pin);
	        }else if(pin.getPinType().equals("video")){
	        	notification.setPinType("video");
	        	notification.setPin(pin);
	        }
	        notification.setMessage(sender.getUserName() + " added new pin");
	        notification.setCreatedAt(LocalDateTime.now()); // Set current timestamp
	        notification.setSender(sender); // Set who sent the notification
	        notification.setReciver(reciver); // Set who receives the notification
	        notification.setRead(false); 
	        
	        NotificationEntity addedNotification = notificationRepo.save(notification); 

	        if(addedNotification!=null) {
	        	
	        	return addedNotification;
	        }else {
	        	return null;
	        }
	        
	    }
	    return null;
	}

	@Override
	public String setIsReadedByNotificationId(Long nId) {
		if(nId != null) {
			NotificationEntity notification = getNotificationById(nId);
			if(notification!=null) {
				notification.setRead(true);
				if(notificationRepo.save(notification)!=null) {
					return "success";
				}else {
					return "failed";
				}
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public NotificationEntity setRemoveFollowerNotification(UserEntity sender, UserEntity reciver) {
		if(sender != null && reciver!=null) {
			NotificationEntity notification = new NotificationEntity();
			
			 notification.setMessage(sender.getUserName() + " removed you from followers");
		        notification.setCreatedAt(LocalDateTime.now()); // Set current timestamp
		        notification.setSender(sender); // Set who sent the notification
		        notification.setReciver(reciver); // Set who receives the notification
		        notification.setRead(false); 
		        NotificationEntity addedNotification = notificationRepo.save(notification); 

		        if(addedNotification!=null) {
		        	
		        	return addedNotification;
		        }else {
		        	return null;
		        }
		        
		    }
		    return null;
	}

}

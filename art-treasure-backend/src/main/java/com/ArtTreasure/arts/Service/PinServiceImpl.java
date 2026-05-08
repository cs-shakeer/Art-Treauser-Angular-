package com.ArtTreasure.arts.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.FollowersListEntity;
import com.ArtTreasure.arts.entity.PinEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.PinRepository;

@Service
public class PinServiceImpl implements PinService {

	@Autowired
	private PinRepository pinRepo;
	@Autowired
	private UserService userServ;
	@Autowired
	private FollowersListService FollowersListServ;
	@Autowired
	private EmailService emailService;
	@Autowired
    private NotificationService notificationService;

	@Override
	public String createPins(PinEntity pin) {

		if (pin != null && pin.getUser()!=null) {
			UserEntity user = userServ.getUserById(pin.getUser().getUserId());
			if(user != null) {
				pin.setCreatedAt(LocalDateTime.now());
			PinEntity newPin = pinRepo.save(pin);
			if (newPin != null) {
				List<FollowersListEntity> followers = FollowersListServ.getUserFollowersListByUserId(user.getUserId());
				followers.forEach(follower ->{
					emailService.pinsMsg(follower.getFollower().getEmail(), user.getUserName(), follower.getFollower().getUserName());
					notificationService.setPinNotification(user, newPin, follower.getFollower());
				});
				
				return "success";
			} else {
				return "failed";
			}
		}
		}
		return null;
	}

	@Override
	public List<PinEntity> getPinsByUserId(Long userId) {
		if (userId != null) {
			List<PinEntity> pins = pinRepo.findByUser_UserId(userId);
			if (pins != null) {
				return pins;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public List<PinEntity> getAllPins() {
		List<PinEntity> allPins = pinRepo.findAll();
		if (allPins != null) {
			return allPins;
		}
		return null;
	}

	@Override
	public String deletePinById(Long pinId) {
		if(pinId!=null) {
			PinEntity existPin = getPinById(pinId);
			if(existPin!=null) {
				pinRepo.deleteById(pinId);
				return "success";
			}else {
				return "!exist";
			}
		}
		return null;
	}

	@Override
	public PinEntity updatePin(PinEntity pin) {
		if(pin!=null) {
			PinEntity existPin = getPinById(pin.getPinId());
			if(existPin!=null) {
				existPin.setTitle(pin.getTitle()!=null?pin.getTitle():existPin.getTitle());
				existPin.setDescription(pin.getDescription()!=null? pin.getDescription():existPin.getDescription());
				
				return pinRepo.save(existPin);
			}
		}
		
		return null;
	}

	@Override
	public PinEntity getPinById(Long pinId) {
		if(pinId!=null) {
			return pinRepo.findById(pinId).orElse(null);
		}
		return null;
	}

}

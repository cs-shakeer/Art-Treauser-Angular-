package com.ArtTreasure.arts.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.AdsEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.AdsReopsitory;

@Service
public class AdsServiceImpl implements AdsService{
	
	@Autowired
	private AdsReopsitory adsRepo;
	
	@Autowired
	private UserService userServ;

	@Override
	public String createAds(AdsEntity ads) {
		if(ads!=null && ads.getUser().getUserId()!=null) {
			UserEntity existUser = userServ.getUserById(ads.getUser().getUserId());
			if(existUser!=null && existUser.isProfessional()) {
				
			}
			
			return "!exist";
		}
		return null;
	}

}

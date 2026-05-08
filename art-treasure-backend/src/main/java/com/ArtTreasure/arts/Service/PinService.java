package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.PinEntity;

public interface PinService {
	
	public String createPins(PinEntity pin);
	
	public PinEntity getPinById(Long pinId);
	
	public List<PinEntity> getPinsByUserId(Long userId);
	
	public PinEntity updatePin(PinEntity pin);
	
	public List<PinEntity> getAllPins();
	
	public String deletePinById(Long pinId);

}

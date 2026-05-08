package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.AdsEntity;
@Repository
public interface AdsReopsitory  extends JpaRepository<AdsEntity, Long> {
	List<AdsEntity> findByUser_UserId(Long userId);

}
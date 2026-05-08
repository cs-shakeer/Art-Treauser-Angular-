package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.PinEntity;
@Repository
public interface PinRepository extends JpaRepository<PinEntity, Long> {
	List<PinEntity> findByUser_UserId(Long userId);

}

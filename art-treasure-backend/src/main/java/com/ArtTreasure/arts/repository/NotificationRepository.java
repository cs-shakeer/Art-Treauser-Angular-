package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.NotificationEntity;
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
	List<NotificationEntity> findByReciver_UserId(Long userId);

}

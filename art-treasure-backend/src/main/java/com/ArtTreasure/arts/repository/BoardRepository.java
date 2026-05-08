package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.BoardEntity;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	List<BoardEntity> findByUser_UserId(Long userId);

}
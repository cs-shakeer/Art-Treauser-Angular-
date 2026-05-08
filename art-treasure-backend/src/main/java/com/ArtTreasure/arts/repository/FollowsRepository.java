package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.FollowsListEntity;
@Repository
public interface FollowsRepository extends JpaRepository<FollowsListEntity, Long> {
    List<FollowsListEntity> findByFollows_UserId(Long followsUserId); // All users followed
    List<FollowsListEntity> findByUser_UserId(Long userId); // All follows of user
}


package com.ArtTreasure.arts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArtTreasure.arts.entity.FollowersListEntity;
@Repository
public interface FollowersRepository extends JpaRepository<FollowersListEntity, Long> {
    List<FollowersListEntity> findByFollower_UserId(Long followerId);   // Who a user follows
    List<FollowersListEntity> findByUser_UserId(Long userId);           // Who follows this user
}


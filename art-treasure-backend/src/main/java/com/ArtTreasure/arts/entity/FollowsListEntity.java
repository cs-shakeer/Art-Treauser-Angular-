package com.ArtTreasure.arts.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follows_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowsListEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("flsId")
	private Long flsId;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "followsId", referencedColumnName = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity follows;

	
}

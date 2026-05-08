package com.ArtTreasure.arts.entity;

import java.time.LocalDateTime;

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
@Table(name="pin_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PinEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("pinId")
	private Long pinId;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("pinType")
	private String pinType;
	
	@JsonProperty("imageUrl")
	private String imageUrl;
	@JsonProperty("videoUrl")
	private String videoUrl;
	@JsonProperty("description")
	private String description;

	// In addition to your code:
	@JsonProperty("createdAt")
	private LocalDateTime createdAt; // Optional, replaces date + time

	@ManyToOne
	@JoinColumn(name = "boardId", referencedColumnName = "boardId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BoardEntity board;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity user;

}

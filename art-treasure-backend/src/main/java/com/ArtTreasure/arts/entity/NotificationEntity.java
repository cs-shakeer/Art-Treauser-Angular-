package com.ArtTreasure.arts.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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
@Table(name = "notifications_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("nId")
	private Long nId;

	@JsonProperty("message")
	private String message;

	@JsonProperty("notificationType")
	@Column(nullable = false)
	private String notificationType;
	
	@ManyToOne
	@JoinColumn(name = "pinId", referencedColumnName = "pinId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PinEntity pin;
	
	@JsonProperty("pinType")
	private String pinType;
	

	@JsonProperty("isRead")
	@Column(name = "is_read", nullable = false)
	private boolean isRead;

	@JsonProperty("createdAt")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "senderId", referencedColumnName = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity sender;

	@ManyToOne
	@JoinColumn(name = "reciverId", referencedColumnName = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity reciver;

}

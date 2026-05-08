package com.ArtTreasure.arts.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userName")
    @Column(nullable = false, unique = true) // Add unique if required
    private String userName;

    @JsonProperty("fullName")
    @Column(nullable = false)
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    @Column(unique = true, nullable = false)
    private String mobile; // Prefer String

    @JsonProperty("profileImg")
    @Column(nullable = false)
    private String profileImg;

    @JsonProperty("password") // Change casing!
    @Column(nullable = false)
    private String password;
    
    @JsonProperty("isProfessional")
	@Column(name = "is_professional", nullable = false)
	private boolean isProfessional;
}


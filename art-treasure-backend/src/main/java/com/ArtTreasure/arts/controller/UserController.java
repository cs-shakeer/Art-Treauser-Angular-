package com.ArtTreasure.arts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ArtTreasure.arts.Service.BoardService;
import com.ArtTreasure.arts.Service.FollowsListService;
import com.ArtTreasure.arts.Service.UserService;
import com.ArtTreasure.arts.entity.BoardEntity;
import com.ArtTreasure.arts.entity.FollowsListEntity;
import com.ArtTreasure.arts.entity.UserEntity;


@RestController
@RequestMapping("/art/api")
public class UserController {

	@Autowired
	private UserService usersService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private FollowsListService FollowsService;

	@PostMapping("/auth/register")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userData) {
		String result = usersService.registerUser(userData);
		if ("success".equals(result)) {
			return ResponseEntity.ok(userData.getUserName() + " registered successfully");
		} else if ("failed".equals(result)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userData.getUserName()+" registration failed");
		} else if ("exist".equals(result)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(userData.getUserName() + " user name already exists");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
		}
	}

	@PostMapping("/auth/login")
	public ResponseEntity<UserEntity> loginUser(@RequestBody UserEntity userData) {
		UserEntity user = usersService.loginUser(userData);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		UserEntity updatedUser = usersService.updateUser(user);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@DeleteMapping("/user/delete")
	public ResponseEntity<String> deleteUser(@RequestBody UserEntity userData) {
		String result = usersService.deleteUserByUserId(userData.getUserId());
		if ("success".equals(result)) {
			return ResponseEntity.ok(userData.getUserName() + " your account successfully deleted");
		} else if ("!exist".equals(result)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userData.getUserName() + " not found..!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
		}
	}

	@PostMapping("/board/addboard")
	public ResponseEntity<String> addBoard(@RequestBody BoardEntity board) {
		if (board != null && board.getUser().getUserId() != null) {
			String result = boardService.createBoard(board);
			if ("success".equals(result)) {
				return ResponseEntity.ok("Board added successfully...📋📋📋");
			} else if ("failed".equals(result)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Board add failed..!");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Given data is empty...please give correct data!");
		}
	}

	@PostMapping("/follow/followes")
	public ResponseEntity<String> setFollowing(@RequestBody FollowsListEntity follow) {
		String result = FollowsService.setFollowing(follow);
		if ("success".equals(result)) {
			return ResponseEntity.ok(
					follow.getUser().getUserName() + " successfully following to " + follow.getFollows().getUserName());
		} else if ("failed".equals(result)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(follow.getUser().getUserName() + "following failed");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(follow.getUser().getUserName() + " or "
					+ follow.getFollows().getUserName() + " following user not exists");

		}
	}
}

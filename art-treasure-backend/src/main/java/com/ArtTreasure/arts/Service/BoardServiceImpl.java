package com.ArtTreasure.arts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArtTreasure.arts.entity.BoardEntity;
import com.ArtTreasure.arts.entity.UserEntity;
import com.ArtTreasure.arts.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private UserService userServ;

	@Autowired
	private EmailService emailService;

	@Override
	public BoardEntity getBoardById(Long boardId) {
		if (boardId != null) {
			return boardRepo.findById(boardId).orElse(null);
		}
		return null;
	}

	@Override
	public String createBoard(BoardEntity board) {

		if (board != null) {
			BoardEntity newBoard = boardRepo.save(board);
			if (newBoard != null) {
				return "success";
			} else {
				return "failed";
			}
		} else {
			return null;
		}

	}

	@Override
	public List<BoardEntity> getUserBoardByUserId(Long userId) {
		UserEntity user = userServ.getUserById(userId);
		if (user != null) {
			List<BoardEntity> allBoards = boardRepo.findByUser_UserId(userId);
			if (allBoards != null) {
				return allBoards;
			} else {
				return null;
			}

		}
		return null;
	}

	@Override
	public List<BoardEntity> getAllBoards() {

		return null;
	}

	@Override
	public BoardEntity editBoard(BoardEntity board) {
		if (board != null && board.getUser().getUserId() != null) {
			BoardEntity existBoard = getBoardById(board.getBoardId());
			if (existBoard != null) {
				existBoard.setName(board.getName() != null ? board.getName() : existBoard.getName());
				existBoard.setDescription(
						board.getDescription() != null ? board.getDescription() : existBoard.getDescription());

				return boardRepo.save(existBoard);

			} else {
				return null;
			}

		}
		return null;
	}

	@Override
	public String deleteBoardById(Long boardId) {
		if (boardId != null) {
			BoardEntity existBoard = getBoardById(boardId);
			UserEntity user = userServ.getUserById(existBoard.getUser().getUserId());
			if (existBoard != null && user != null) {
				boardRepo.deleteById(boardId);
				emailService.userBoardDeleteMsg(user.getEmail(), user.getUserName(), existBoard.getName());
				return "success";
			} else {
				return "!exist";
			}
		} else {
			return null;
		}
	}

}

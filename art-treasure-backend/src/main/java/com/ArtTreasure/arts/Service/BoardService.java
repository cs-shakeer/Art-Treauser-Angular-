package com.ArtTreasure.arts.Service;

import java.util.List;

import com.ArtTreasure.arts.entity.BoardEntity;

public interface BoardService {
	
	public BoardEntity getBoardById(Long board);

	public List<BoardEntity> getUserBoardByUserId(Long userId);

	public String createBoard(BoardEntity board);
	
	public String deleteBoardById(Long boardId);
	
	public BoardEntity editBoard(BoardEntity board);

	public List<BoardEntity> getAllBoards();

}

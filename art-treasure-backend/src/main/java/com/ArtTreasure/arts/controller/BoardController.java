package com.ArtTreasure.arts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ArtTreasure.arts.Service.BoardService;
import com.ArtTreasure.arts.entity.BoardEntity;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/art/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardEntity>> getAllBoards() {
        List<BoardEntity> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable Long boardId) {
        BoardEntity board = boardService.getBoardById(boardId);
        if (board != null) {
            return ResponseEntity.ok(board);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BoardEntity>> getBoardsByUserId(@PathVariable Long userId) {
        List<BoardEntity> boards = boardService.getUserBoardByUserId(userId);
        return ResponseEntity.ok(boards);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBoard(@RequestBody BoardEntity board) {
        String result = boardService.createBoard(board);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Board added successfully! 📋");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add board.");
    }

    @PutMapping("/update")
    public ResponseEntity<BoardEntity> updateBoard(@RequestBody BoardEntity board) {
        BoardEntity updated = boardService.editBoard(board);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        String result = boardService.deleteBoardById(boardId);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Board deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found.");
    }
}

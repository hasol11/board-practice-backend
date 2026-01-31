package com.practice.board.board.controller;

import com.practice.board.board.dto.BoardDto;
import com.practice.board.board.service.BoardService;
import com.practice.board.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/list")
    public ResponseEntity<ResponseDto<?>> getBoardList(){
        return boardService.getBoardList();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> getBoardDetail(@PathVariable Integer boardId){return boardService.getBoardDetail(boardId);}

    @PostMapping("/write")
    public ResponseEntity<ResponseDto<?>> writeBoard(@RequestBody BoardDto.BoardWriteDto boardwriteDto){
        return boardService.writeBoard(boardwriteDto);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> updateBoard(@PathVariable Integer boardId, @RequestBody BoardDto.BoardWriteDto boardwriteDto){
        return boardService.updateBoard(boardId, boardwriteDto);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> deleteBoard(@PathVariable Integer boardId) {
        return boardService.deleteBoard(boardId);
    }
}

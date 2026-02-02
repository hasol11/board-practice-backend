package com.practice.board.board.service;

import com.practice.board.board.dto.BoardDto;
import com.practice.board.board.repository.BoardRepository;
import com.practice.board.common.dto.ResponseDto;
import com.practice.board.common.entity.Board;
import com.practice.board.common.entity.User;
import com.practice.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDto<?>> getBoardList(String keyword) {
        List<Board> boards;
        if(keyword == null || keyword.isEmpty()) {
            boards = boardRepository.findAllByOrderByCreatedAtDesc();
        } else {
            boards = boardRepository.findByTitleContainingOrderByCreatedAtDesc(keyword);
        }
        List<BoardDto.BoardListDto> boardListDtos = boards.stream()
                .map(BoardDto.BoardListDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ResponseDto<>(200, "게시판 목록 조회 성공", boardListDtos)
        );
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDto<?>> getBoardDetail(Integer boardId) {
        // DB가 INT이므로 Long.valueOf 없이 바로 boardId(Integer)를 사용합니다.
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판을 찾을 수 없습니다."));

        return ResponseEntity.ok(
                new ResponseDto<>(200, "게시판 상세 조회 성공", new BoardDto.BoardDetailDto(board))
        );
    }

    @Transactional
    public ResponseEntity<ResponseDto<?>> writeBoard(BoardDto.BoardWriteDto boardwriteDto) {
        User user = userRepository.findById(boardwriteDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Board board = new Board();
        board.setTitle(boardwriteDto.getTitle());
        board.setContent(boardwriteDto.getContent());
        board.setUser(user);

        boardRepository.save(board);

        return ResponseEntity.ok(
                new ResponseDto<>(200, "게시판 작성 성공", null)
        );
    }

    public ResponseEntity<ResponseDto<?>> updateBoard(Integer boardId, BoardDto.BoardWriteDto boardwriteDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판을 찾을 수 없습니다."));

        board.setTitle(boardwriteDto.getTitle());
        board.setContent(boardwriteDto.getContent());

        boardRepository.save(board);

        return ResponseEntity.ok(
                new ResponseDto<>(200, "게시판 수정 성공", null)
        );
    }

    public ResponseEntity<ResponseDto<?>> deleteBoard(Integer boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판을 찾을 수 없습니다."));

        boardRepository.delete(board);

        return ResponseEntity.ok(
                new ResponseDto<>(200, "게시판 삭제 성공", null)
        );
    }
}


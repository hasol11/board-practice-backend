package com.practice.board.board.dto;

import com.practice.board.common.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class BoardDto {
    @Getter
    @NoArgsConstructor
    public static class BoardListDto {
        private Integer boardId;
        private String title;
        private String nickname;
        private LocalDateTime createdAt;
        private Integer viewCount;

        public BoardListDto(Board board) {
            this.boardId = board.getBoardId();
            this.title = board.getTitle();
            this.nickname = board.getUser().getNickname();
            this.createdAt = board.getCreatedAt();
            this.viewCount = board.getViewCount();
        }
    }
    @Getter
    @NoArgsConstructor
    public static class BoardDetailDto{
        private Integer boardId;
        private String title;
        private String content;
        private String nickname;
        private Long userId;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;
        private Integer viewCount;

        public BoardDetailDto(Board board) {
            this.boardId = board.getBoardId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.nickname = board.getUser().getNickname();
            this.userId = board.getUser().getUserId();
            this.createdAt = board.getCreatedAt();
            this.updateAt = board.getUpdateAt();
            this.viewCount = board.getViewCount();
        }
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class BoardWriteDto{
        private String title;
        private String content;
        private Long userId;
    }
}

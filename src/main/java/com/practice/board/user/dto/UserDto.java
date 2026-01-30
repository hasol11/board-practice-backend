package com.practice.board.user.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter @Setter
    public static class Login {
        private String nickname;
        private String password;
    }
}

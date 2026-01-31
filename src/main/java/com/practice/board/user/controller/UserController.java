package com.practice.board.user.controller;

import com.practice.board.common.dto.ResponseDto;
import com.practice.board.user.dto.UserDto;
import com.practice.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<?>> login(@RequestBody UserDto.Login logindto) {
        return userService.login(logindto);
    }
    @PostMapping("/findNickName")
    public ResponseEntity<ResponseDto<?>> findNickName(@RequestBody UserDto.FindNickName findNickNamedto) {
        return userService.findNickName(findNickNamedto);
    }
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<?>> signup(@RequestBody UserDto.Login signUpdto) {
        return userService.signup(signUpdto);
    }
}

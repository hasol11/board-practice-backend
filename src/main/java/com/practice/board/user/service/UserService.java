package com.practice.board.user.service;

import com.practice.board.entity.User;
import com.practice.board.user.dto.UserDto;
import com.practice.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String login(UserDto.Login logindto) {
        return userRepository.findByNickname(logindto.getNickname())
                .map(user -> {
                    if (!user.getPassword().equals(logindto.getPassword())) {
                        return "비밀번호가 일치하지 않습니다.";
                    }
                    return "로그인 성공! " + user.getNickname() + "님 환영합니다.";
                })
                .orElse("존재하지 않는 유저입니다.");
    }
}

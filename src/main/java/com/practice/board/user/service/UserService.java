package com.practice.board.user.service;

import com.practice.board.common.dto.ResponseDto;
import com.practice.board.common.entity.User;
import com.practice.board.user.dto.UserDto;
import com.practice.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<ResponseDto<?>> login(UserDto.Login logindto) {
        User user = userRepository.findByNickname(logindto.getNickname()).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(404, "사용자를 찾을 수 없습니다.", null));
        }

        if (!user.getPassword().equals(logindto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDto<>(401, "비밀번호가 일치하지 않습니다.", null));
        }

        return ResponseEntity.ok(
                new ResponseDto<>(200, "로그인 성공", Map.of("id", user.getUserId(), "nickname", user.getNickname()))
        );
    }

    public ResponseEntity<ResponseDto<?>> findNickName(UserDto.FindNickName findNickNamedto) {
        User user = userRepository.findByNickname(findNickNamedto.getNickname()).orElse(null);

        if (user != null) {
            return ResponseEntity.ok(
                    new ResponseDto<>(200, "이미 사용 중인 닉네임입니다.", Map.of("available", false))
            );
        }
        return ResponseEntity.ok(
                new ResponseDto<>(200, "사용 가능한 닉네임입니다.", Map.of("available", true))
        );
    }

    public ResponseEntity<ResponseDto<?>> signup(UserDto.Login signUpdto) {
        if (userRepository.findByNickname(signUpdto.getNickname()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDto<>(409, "이미 존재하는 닉네임입니다.", null));
        }
        User user = new User();
        user.setNickname(signUpdto.getNickname());
        user.setPassword(signUpdto.getPassword());

        userRepository.save(user);

        return ResponseEntity.ok(
                new ResponseDto<>(200, "회원가입 성공", null)
        );
    }
}
package kr.ac.phdljr.boardrefactor.domain.user.controller;

import jakarta.validation.Valid;
import kr.ac.phdljr.boardrefactor.domain.user.dto.request.NicknameCheckRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.dto.request.SignUpRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        userService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/nickname/check")
    public ResponseEntity<Void> checkNickname(@Valid @RequestBody NicknameCheckRequestDto nicknameCheckRequestDto){
        userService.checkNickname(nicknameCheckRequestDto.nickname());
        return ResponseEntity.ok().build();
    }
}

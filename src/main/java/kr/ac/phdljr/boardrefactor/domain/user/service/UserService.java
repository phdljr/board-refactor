package kr.ac.phdljr.boardrefactor.domain.user.service;

import kr.ac.phdljr.boardrefactor.domain.user.dto.request.SignUpRequestDto;

public interface UserService {

    void signUp(SignUpRequestDto signUpRequestDto);

    void checkNickname(String nickname);
}

package kr.ac.phdljr.boardrefactor.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ILLEGAL_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호에 닉네임을 포함시킬 수 없습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."), 
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    BAD_LOGIN(HttpStatus.BAD_REQUEST,"이메일 또는 패스워드를 확인해주세요." );

    private final HttpStatus httpStatus;
    private final String message;
}

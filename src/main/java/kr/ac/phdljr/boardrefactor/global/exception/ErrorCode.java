package kr.ac.phdljr.boardrefactor.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400
    ILLEGAL_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호에 닉네임을 포함시킬 수 없습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    BAD_LOGIN(HttpStatus.BAD_REQUEST, "이메일 또는 패스워드를 확인해주세요."),
    NOT_MATCH_USER(HttpStatus.BAD_REQUEST, "작성자만 수정 및 삭제를 할 수 있습니다."),
    ALREADY_EXIST_BOARD_LIKE(HttpStatus.BAD_REQUEST, "이미 게시글에 대한 좋아요가 있습니다."),
    NOT_MATCH_CODE(HttpStatus.BAD_REQUEST, "코드가 일치하지 않습니다."),
    ALREADY_CONFIRM(HttpStatus.BAD_REQUEST, "이미 인증에 성공했습니다."),
    NOT_CONFIRM_EMAIL(HttpStatus.BAD_REQUEST, "이메일이 인증되지 않았습니다."),

    // 404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "사용자를 찾지 못하였습니다."),
    NOT_FOUND_BOARD(HttpStatus.NOT_FOUND, "게시글을 찾지 못하였습니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "댓글을 찾지 못하였습니다."),
    NOT_FOUND_BOARD_LIKE(HttpStatus.NOT_FOUND, "게시글에 대한 좋아요를 찾지 못하였습니다."),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "이메일을 찾지 못하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

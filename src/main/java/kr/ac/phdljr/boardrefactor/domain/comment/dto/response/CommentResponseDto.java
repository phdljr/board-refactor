package kr.ac.phdljr.boardrefactor.domain.comment.dto.response;

import java.time.LocalDateTime;

public record CommentResponseDto(
    String nickname,
    String content,
    LocalDateTime createdAt
) {

}

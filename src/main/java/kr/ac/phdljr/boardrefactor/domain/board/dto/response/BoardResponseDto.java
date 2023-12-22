package kr.ac.phdljr.boardrefactor.domain.board.dto.response;

import java.time.LocalDateTime;

public record BoardResponseDto(
    String title,
    String nickname,
    String content,
    LocalDateTime createdAt
) {

}

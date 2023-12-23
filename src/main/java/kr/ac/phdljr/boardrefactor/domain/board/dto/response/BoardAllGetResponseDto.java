package kr.ac.phdljr.boardrefactor.domain.board.dto.response;

import java.time.LocalDateTime;

public record BoardAllGetResponseDto(
    String title,
    String nickname,
    LocalDateTime createdAt
) {

}

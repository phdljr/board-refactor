package kr.ac.phdljr.boardrefactor.domain.board.dto.request;

import jakarta.validation.constraints.Size;

public record BoardUpdateRequestDto(
    @Size(max = 500)
    String title,

    @Size(max = 5000)
    String content
) {

}

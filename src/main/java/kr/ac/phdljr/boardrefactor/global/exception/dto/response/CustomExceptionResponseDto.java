package kr.ac.phdljr.boardrefactor.global.exception.dto.response;

import lombok.Builder;

@Builder
public record CustomExceptionResponseDto(
    int status,
    String name,
    String message
) {

}

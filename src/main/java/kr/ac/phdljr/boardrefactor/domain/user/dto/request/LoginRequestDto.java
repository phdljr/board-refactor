package kr.ac.phdljr.boardrefactor.domain.user.dto.request;

import lombok.Builder;

@Builder
public record LoginRequestDto(
    String email,
    String password
) {

}

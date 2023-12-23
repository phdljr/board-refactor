package kr.ac.phdljr.boardrefactor.domain.email.dto.request;

import jakarta.validation.constraints.Email;

public record EmailAuthRequestDto(
    @Email(message = "이메일 형식에 맞지 않습니다.")
    String email
) {

}

package kr.ac.phdljr.boardrefactor.domain.email.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EmailAuthConfirmRequestDto(
    @Email
    String email,
    @Size(min = 6, max = 6)
    String code
) {

}

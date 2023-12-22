package kr.ac.phdljr.boardrefactor.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SignUpRequestDto(
    @Email(message = "이메일 형식에 맞지 않습니다.")
    String email,

    @Min(value = 4, message = "비밀번호는 최소 4자 이상으로 구성돼야 합니다.")
    String password,

    @Min(value = 4, message = "비밀번호는 최소 4자 이상으로 구성돼야 합니다.")
    String passwordConfirm,

    @Size(min = 3, message = "닉네임은 최소 3자 이상으로 구성돼야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "닉네임 형식에 맞지 않습니다.")
    String nickname
) {

}

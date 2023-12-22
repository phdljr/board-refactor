package kr.ac.phdljr.boardrefactor.global.exception.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ValidationExceptionResponseDto(
    int status,
    List<String> description
) {

}

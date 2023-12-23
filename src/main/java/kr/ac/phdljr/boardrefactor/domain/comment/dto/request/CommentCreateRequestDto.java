package kr.ac.phdljr.boardrefactor.domain.comment.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CommentCreateRequestDto(
    @Size(max = 5000)
    String content
) {

}

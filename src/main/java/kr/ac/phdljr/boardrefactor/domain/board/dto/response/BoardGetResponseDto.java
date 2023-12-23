package kr.ac.phdljr.boardrefactor.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;

public record BoardGetResponseDto(
    String title,
    String nickname,
    String content,
    LocalDateTime createdAt,
    List<CommentResponseDto> comments
) {

}

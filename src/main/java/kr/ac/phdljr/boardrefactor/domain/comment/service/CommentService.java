package kr.ac.phdljr.boardrefactor.domain.comment.service;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    void createComment(Long boardId, CommentCreateRequestDto commentCreateRequestDto, User user);

    void updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, User user);

    void deleteComment(Long commentId, User user);

    List<CommentResponseDto> getComments(Long boardId, Pageable pageable);
}

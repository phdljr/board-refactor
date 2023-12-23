package kr.ac.phdljr.boardrefactor.domain.comment.service.impl;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotFoundBoardException;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotMatchUserException;
import kr.ac.phdljr.boardrefactor.domain.board.repository.BoardRepository;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.request.CommentUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.comment.entity.Comment;
import kr.ac.phdljr.boardrefactor.domain.comment.exception.NotFoundCommentException;
import kr.ac.phdljr.boardrefactor.domain.comment.mapper.CommentMapper;
import kr.ac.phdljr.boardrefactor.domain.comment.repository.CommentRepository;
import kr.ac.phdljr.boardrefactor.domain.comment.service.CommentService;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.domain.user.repository.UserRepository;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public void createComment(final Long boardId,
        final CommentCreateRequestDto commentCreateRequestDto,
        final User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        Comment comment = CommentMapper.INSTANCE.toComment(commentCreateRequestDto, user, board);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(final Long commentId,
        final CommentUpdateRequestDto commentUpdateRequestDto,
        final User user) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new NotFoundCommentException(ErrorCode.NOT_FOUND_COMMENT));

        if(!comment.getUser().getId().equals(user.getId())){
            throw new NotMatchUserException(ErrorCode.NOT_MATCH_USER);
        }

        comment.update(commentUpdateRequestDto.content());
    }

    @Override
    @Transactional
    public void deleteComment(final Long commentId, final User user) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new NotFoundCommentException(ErrorCode.NOT_FOUND_COMMENT));

        if(!comment.getUser().getId().equals(user.getId())){
            throw new NotMatchUserException(ErrorCode.NOT_MATCH_USER);
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponseDto> getComments(final Long boardId, final Pageable pageable) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        List<Comment> comments = commentRepository.findAllByBoard(board, pageable);

        return CommentMapper.INSTANCE.toCommentResponseDtoList(comments);
    }
}

package kr.ac.phdljr.boardrefactor.domain.board.service.impl;

import kr.ac.phdljr.boardrefactor.domain.comment.dto.response.CommentResponseDto;
import kr.ac.phdljr.boardrefactor.domain.comment.entity.Comment;
import kr.ac.phdljr.boardrefactor.domain.comment.repository.CommentRepository;
import kr.ac.phdljr.boardrefactor.domain.comment.service.CommentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardAllGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotFoundBoardException;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotFoundUserException;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotMatchUserException;
import kr.ac.phdljr.boardrefactor.domain.board.mapper.BoardMapper;
import kr.ac.phdljr.boardrefactor.domain.board.repository.BoardRepository;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.service.BoardService;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.domain.user.repository.UserRepository;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "boardService")
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createBoard(final BoardCreateRequestDto boardCreateRequestDto, final User user) {
        User findUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));
        Board board = BoardMapper.INSTANCE.toBoard(boardCreateRequestDto, findUser);
        boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardGetResponseDto getBoard(final Long boardId) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        List<Comment> comments = commentRepository.findAllByBoardId(boardId,
            PageRequest.of(0, 20));

        return BoardMapper.INSTANCE.toBoardResponseDto(board, comments);
    }

    @Override
    @Transactional
    public void updateBoard(final Long boardId, final BoardUpdateRequestDto boardUpdateRequestDto,
        final User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        if(!board.getUser().getId().equals(user.getId())){
            throw new NotMatchUserException(ErrorCode.NOT_MATCH_USER);
        }

        board.update(boardUpdateRequestDto.title(), boardUpdateRequestDto.content());
    }

    @Override
    @Transactional
    public void deleteBoard(final Long boardId, final User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        if(!board.getUser().getId().equals(user.getId())){
            throw new NotMatchUserException(ErrorCode.NOT_MATCH_USER);
        }

        boardRepository.delete(board);
    }

    @Override
    public List<BoardAllGetResponseDto> getBoardAll(final Pageable pageable) {
        List<Board> boards = boardRepository.findByOrderByCreatedAtDesc(pageable);
        return BoardMapper.INSTANCE.toBoardAllGetResponseDtoList(boards);
    }
}

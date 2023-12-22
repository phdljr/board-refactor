package kr.ac.phdljr.boardrefactor.domain.board.service.impl;

import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardResponseDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    public void createBoard(final BoardCreateRequestDto boardCreateRequestDto, final User user) {
        User findUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));
        Board board = BoardMapper.INSTANCE.toBoard(boardCreateRequestDto, findUser);
        boardRepository.save(board);
    }

    @Override
    public BoardResponseDto getBoard(final Long boardId) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));
        return BoardMapper.INSTANCE.toBoardResponseDto(board);
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
}

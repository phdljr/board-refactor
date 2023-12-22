package kr.ac.phdljr.boardrefactor.domain.board.service;

import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;

public interface BoardService {
    void createBoard(BoardCreateRequestDto boardCreateRequestDto, final User user);

    BoardResponseDto getBoard(Long boardId);

    void updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, User user);

    void deleteBoard(Long boardId, User user);
}

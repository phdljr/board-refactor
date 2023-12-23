package kr.ac.phdljr.boardrefactor.domain.board.service;

import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardUpdateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardAllGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.response.BoardGetResponseDto;
import kr.ac.phdljr.boardrefactor.domain.board.dto.request.BoardCreateRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.global.page.PageRequestDto;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    void createBoard(BoardCreateRequestDto boardCreateRequestDto, final User user);

    BoardGetResponseDto getBoard(Long boardId);

    void updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, User user);

    void deleteBoard(Long boardId, User user);

    List<BoardAllGetResponseDto> getBoardAll(Pageable pageable);
}

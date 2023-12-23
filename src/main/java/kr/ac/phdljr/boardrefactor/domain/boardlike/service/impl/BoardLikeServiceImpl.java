package kr.ac.phdljr.boardrefactor.domain.boardlike.service.impl;

import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotFoundBoardException;
import kr.ac.phdljr.boardrefactor.domain.board.exception.NotFoundUserException;
import kr.ac.phdljr.boardrefactor.domain.board.repository.BoardRepository;
import kr.ac.phdljr.boardrefactor.domain.boardlike.entity.BoardLike;
import kr.ac.phdljr.boardrefactor.domain.boardlike.exception.AlreadyExistsBoardLikeException;
import kr.ac.phdljr.boardrefactor.domain.boardlike.exception.NotFoundBoardLikeException;
import kr.ac.phdljr.boardrefactor.domain.boardlike.repository.BoardLikeRepository;
import kr.ac.phdljr.boardrefactor.domain.boardlike.service.BoardLikeService;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.domain.user.repository.UserRepository;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Override
    public void createBoardLike(final Long boardId, final User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        User findUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        if (boardLikeRepository.existsByUserAndBoard(findUser, board)) {
            throw new AlreadyExistsBoardLikeException(ErrorCode.ALREADY_EXIST_BOARDLIKE);
        }

        BoardLike boardLike = BoardLike.builder()
            .board(board)
            .user(findUser)
            .build();

        boardLikeRepository.save(boardLike);
    }

    @Override
    @Transactional
    public void deleteBoardLike(final Long boardId, final User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NotFoundBoardException(ErrorCode.NOT_FOUND_BOARD));

        User findUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));

        BoardLike boardLike = boardLikeRepository.findByUserAndBoard(findUser, board)
            .orElseThrow(() -> new NotFoundBoardLikeException(ErrorCode.NOT_FOUND_BOARD_LIKE));

        boardLikeRepository.delete(boardLike);
    }
}

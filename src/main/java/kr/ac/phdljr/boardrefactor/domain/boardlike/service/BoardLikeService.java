package kr.ac.phdljr.boardrefactor.domain.boardlike.service;

import kr.ac.phdljr.boardrefactor.domain.user.entity.User;

public interface BoardLikeService {

    void createBoardLike(Long boardId, User user);

    void deleteBoardLike(Long boardId, User user);
}

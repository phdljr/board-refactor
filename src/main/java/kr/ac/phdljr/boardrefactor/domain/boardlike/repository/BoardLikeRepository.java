package kr.ac.phdljr.boardrefactor.domain.boardlike.repository;

import java.util.Optional;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.boardlike.entity.BoardLike;
import kr.ac.phdljr.boardrefactor.domain.boardlike.entity.BoardLikeId;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, BoardLikeId> {

    boolean existsByUserAndBoard(User findUser, Board board);

    Optional<BoardLike> findByUserAndBoard(User findUser, Board board);
}

package kr.ac.phdljr.boardrefactor.domain.board.repository;

import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}

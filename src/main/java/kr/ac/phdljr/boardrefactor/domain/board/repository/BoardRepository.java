package kr.ac.phdljr.boardrefactor.domain.board.repository;

import java.time.LocalDateTime;
import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByOrderByCreatedAtDesc(Pageable pageable);

    List<Board> findByModifiedAtAfter(LocalDateTime localDateTime);
}

package kr.ac.phdljr.boardrefactor.domain.board.schedule;

import java.time.LocalDateTime;
import java.util.List;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BoardScheduler {

    private final BoardRepository boardRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteBoardAfter90Days() {
        List<Board> boards = boardRepository.findByModifiedAtAfter(
            LocalDateTime.now().minusDays(90));

        boardRepository.deleteAllInBatch(boards);
    }
}

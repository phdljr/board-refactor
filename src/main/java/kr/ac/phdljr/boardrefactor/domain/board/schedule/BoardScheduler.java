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

    // 매일 새벽 6시마다 작업 실행
    // 사용률이 적은 시간대라고 예상
    @Scheduled(cron = "0 0 6 * * *")
    public void deleteBoardAfter90Days() {
        List<Board> boards = boardRepository.findByModifiedAtBefore(
            LocalDateTime.now().minusDays(90));

        boardRepository.deleteAllInBatch(boards);
    }
}

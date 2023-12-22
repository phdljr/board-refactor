package kr.ac.phdljr.boardrefactor.domain.boardlike;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;

@Entity
@Table(name = "TB_BOARD_LIKE")
@IdClass(UserBoardId.class)
public class BoardLike {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}

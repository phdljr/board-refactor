package kr.ac.phdljr.boardrefactor.domain.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.ac.phdljr.boardrefactor.domain.board.entity.Board;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.global.entity.BaseEntity;

@Entity
@Table(name = "TB_COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}

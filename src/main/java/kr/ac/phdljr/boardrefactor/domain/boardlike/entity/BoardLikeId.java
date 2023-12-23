package kr.ac.phdljr.boardrefactor.domain.boardlike.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLikeId implements Serializable {

    private Long user;
    private Long board;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoardLikeId that = (BoardLikeId) o;
        return Objects.equals(user, that.user) && Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, board);
    }
}

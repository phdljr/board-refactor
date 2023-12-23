package kr.ac.phdljr.boardrefactor.domain.boardlike.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotFoundBoardLikeException extends CustomException {

    public NotFoundBoardLikeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

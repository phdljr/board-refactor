package kr.ac.phdljr.boardrefactor.domain.board.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotMatchUserException extends CustomException {

    public NotMatchUserException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

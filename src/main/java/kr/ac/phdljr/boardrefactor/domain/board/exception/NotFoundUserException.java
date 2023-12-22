package kr.ac.phdljr.boardrefactor.domain.board.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotFoundUserException extends CustomException {

    public NotFoundUserException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

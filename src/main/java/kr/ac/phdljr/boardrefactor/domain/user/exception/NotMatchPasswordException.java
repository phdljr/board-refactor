package kr.ac.phdljr.boardrefactor.domain.user.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotMatchPasswordException extends CustomException {

    public NotMatchPasswordException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

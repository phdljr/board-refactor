package kr.ac.phdljr.boardrefactor.domain.email.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotMatchCodeException extends CustomException {

    public NotMatchCodeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

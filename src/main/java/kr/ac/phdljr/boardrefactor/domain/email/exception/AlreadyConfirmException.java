package kr.ac.phdljr.boardrefactor.domain.email.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class AlreadyConfirmException extends CustomException {

    public AlreadyConfirmException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

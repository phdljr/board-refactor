package kr.ac.phdljr.boardrefactor.domain.user.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class AlreadyExistsEmailException extends CustomException {

    public AlreadyExistsEmailException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

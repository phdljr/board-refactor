package kr.ac.phdljr.boardrefactor.domain.user.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class IllegalFormatPassword extends CustomException {

    public IllegalFormatPassword(ErrorCode errorCode) {
        super(errorCode);
    }
}

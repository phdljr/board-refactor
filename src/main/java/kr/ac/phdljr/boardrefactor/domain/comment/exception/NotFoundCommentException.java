package kr.ac.phdljr.boardrefactor.domain.comment.exception;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;

public class NotFoundCommentException extends CustomException {

    public NotFoundCommentException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

package kr.ac.phdljr.boardrefactor.global.exception.handler;

import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import kr.ac.phdljr.boardrefactor.global.exception.dto.response.CustomExceptionResponseDto;
import kr.ac.phdljr.boardrefactor.global.exception.dto.response.ValidationExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponseDto> handleCustomException(
        CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
            .status(exception.getErrorCode().getHttpStatus())
            .body(CustomExceptionResponseDto.builder()
                .status(errorCode.getHttpStatus().value())
                .name(errorCode.name())
                .message(errorCode.getMessage())
                .build()
            );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> methodValidException(
        MethodArgumentNotValidException e) {
        ValidationExceptionResponseDto responseDto = ValidationExceptionResponseDto.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .description(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList()
            )
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}

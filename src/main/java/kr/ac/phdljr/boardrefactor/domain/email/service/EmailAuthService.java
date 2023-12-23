package kr.ac.phdljr.boardrefactor.domain.email.service;

import jakarta.mail.MessagingException;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthConfirmRequestDto;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthRequestDto;

public interface EmailAuthService {

    void createEmailAuth(EmailAuthRequestDto emailAuthRequestDto) throws MessagingException;

    void confirmEmailAuth(EmailAuthConfirmRequestDto emailAuthConfirmRequestDto);
}

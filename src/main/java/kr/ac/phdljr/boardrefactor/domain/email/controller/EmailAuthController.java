package kr.ac.phdljr.boardrefactor.domain.email.controller;

import jakarta.mail.MessagingException;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthConfirmRequestDto;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthRequestDto;
import kr.ac.phdljr.boardrefactor.domain.email.service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EmailAuthController {

    private final EmailAuthService emailAuthService;

    @PostMapping("/auth/email")
    public ResponseEntity<Void> createEmailAuth(@RequestBody EmailAuthRequestDto emailAuthRequestDto)
        throws MessagingException {
        emailAuthService.createEmailAuth(emailAuthRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auth/email/confirm")
    public ResponseEntity<Void> confirmEmailAuth(@RequestBody EmailAuthConfirmRequestDto emailAuthConfirmRequestDto){
        emailAuthService.confirmEmailAuth(emailAuthConfirmRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

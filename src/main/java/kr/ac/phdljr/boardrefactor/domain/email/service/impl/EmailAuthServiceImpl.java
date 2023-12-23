package kr.ac.phdljr.boardrefactor.domain.email.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import java.util.Optional;
import java.util.UUID;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthConfirmRequestDto;
import kr.ac.phdljr.boardrefactor.domain.email.dto.request.EmailAuthRequestDto;
import kr.ac.phdljr.boardrefactor.domain.email.entity.EmailAuth;
import kr.ac.phdljr.boardrefactor.domain.email.exception.AlreadyConfirmException;
import kr.ac.phdljr.boardrefactor.domain.email.exception.NotFoundEmailException;
import kr.ac.phdljr.boardrefactor.domain.email.exception.NotMatchCodeException;
import kr.ac.phdljr.boardrefactor.domain.email.repository.EmailAuthRedisRepository;
import kr.ac.phdljr.boardrefactor.domain.email.service.EmailAuthService;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailAuthServiceImpl implements EmailAuthService {

    private final EmailAuthRedisRepository emailAuthRedisRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void createEmailAuth(final EmailAuthRequestDto emailAuthRequestDto)
        throws MessagingException {
        Optional<EmailAuth> emailAuth = emailAuthRedisRepository.findByEmail(
            emailAuthRequestDto.email());
        emailAuth.ifPresent(emailAuthRedisRepository::delete);

        String code = UUID.randomUUID().toString().substring(0, 6);

        EmailAuth newEmailAuth = EmailAuth.builder()
            .email(emailAuthRequestDto.email())
            .code(code)
            .check(false)
            .build();

        emailAuthRedisRepository.save(newEmailAuth);
        sendEmail(emailAuthRequestDto.email(), code);
    }

    private void sendEmail(String to, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(email);
        message.addRecipients(RecipientType.TO, to);
        message.setSubject("Board Refactor 이메일 인증");
        message.setText(String.format("인증 코드: %s", code), "UTF-8");

        javaMailSender.send(message);
    }

    @Override
    public void confirmEmailAuth(final EmailAuthConfirmRequestDto emailAuthConfirmRequestDto) {
        EmailAuth emailAuth = emailAuthRedisRepository.findByEmail(
            emailAuthConfirmRequestDto.email()).orElseThrow(() -> new NotFoundEmailException(
            ErrorCode.NOT_FOUND_EMAIL));

        if(emailAuth.isCheck()){
            throw new AlreadyConfirmException(ErrorCode.ALREADY_CONFIRM);
        }

        if(!emailAuth.getCode().equals(emailAuthConfirmRequestDto.code())){
            throw new NotMatchCodeException(ErrorCode.NOT_MATCH_CODE);
        }

        emailAuthRedisRepository.save(emailAuth.confirm());
    }
}

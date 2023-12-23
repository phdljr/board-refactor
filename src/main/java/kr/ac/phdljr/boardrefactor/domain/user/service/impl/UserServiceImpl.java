package kr.ac.phdljr.boardrefactor.domain.user.service.impl;

import java.util.Objects;
import kr.ac.phdljr.boardrefactor.domain.email.entity.EmailAuth;
import kr.ac.phdljr.boardrefactor.domain.email.exception.NotConfirmEmailAuthException;
import kr.ac.phdljr.boardrefactor.domain.email.exception.NotFoundEmailException;
import kr.ac.phdljr.boardrefactor.domain.email.repository.EmailAuthRedisRepository;
import kr.ac.phdljr.boardrefactor.domain.email.service.EmailAuthService;
import kr.ac.phdljr.boardrefactor.domain.user.dto.request.SignUpRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import kr.ac.phdljr.boardrefactor.domain.user.entity.UserRole;
import kr.ac.phdljr.boardrefactor.domain.user.exception.AlreadyExistsEmailException;
import kr.ac.phdljr.boardrefactor.domain.user.exception.DuplicateNicknameException;
import kr.ac.phdljr.boardrefactor.domain.user.exception.IllegalFormatPassword;
import kr.ac.phdljr.boardrefactor.domain.user.exception.NotMatchPasswordException;
import kr.ac.phdljr.boardrefactor.domain.user.repository.UserRepository;
import kr.ac.phdljr.boardrefactor.domain.user.service.UserService;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthRedisRepository emailAuthRedisRepository;

    @Override
    public void signUp(final SignUpRequestDto signUpRequestDto) {
        // 이메일 중복 체크
        if(userRepository.existsByEmail(signUpRequestDto.email())){
            throw new AlreadyExistsEmailException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        // 닉네임이 중복되지 않는지 확인
        checkNickname(signUpRequestDto.nickname());

        // 비밀번호에 닉네임이 포함돼있지 않은지 확인
        if(signUpRequestDto.password().contains(signUpRequestDto.nickname())){
            throw new IllegalFormatPassword(ErrorCode.ILLEGAL_PASSWORD);
        }

        // 비밀번호가 같은지 확인
        if(!Objects.equals(signUpRequestDto.password(), signUpRequestDto.passwordConfirm())){
            throw new NotMatchPasswordException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        // 이메일이 인증됐는지 확인
        EmailAuth emailAuth = emailAuthRedisRepository.findByEmail(signUpRequestDto.email())
            .orElseThrow(() -> new NotFoundEmailException(ErrorCode.NOT_FOUND_EMAIL));
        if(!emailAuth.isCheck()){
            throw new NotConfirmEmailAuthException(ErrorCode.NOT_CONFIRM_EMAIL);
        }

        // 저장
        User user = User.builder()
            .email(signUpRequestDto.email())
            .nickname(signUpRequestDto.nickname())
            .password(passwordEncoder.encode(signUpRequestDto.password()))
            .role(UserRole.USER)
            .build();

        userRepository.save(user);
        emailAuthRedisRepository.delete(emailAuth);
    }

    @Override
    public void checkNickname(final String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }
}

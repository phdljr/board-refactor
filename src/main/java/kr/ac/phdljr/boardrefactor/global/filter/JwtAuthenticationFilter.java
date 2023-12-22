package kr.ac.phdljr.boardrefactor.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import kr.ac.phdljr.boardrefactor.domain.user.dto.request.LoginRequestDto;
import kr.ac.phdljr.boardrefactor.domain.user.entity.UserRole;
import kr.ac.phdljr.boardrefactor.global.exception.CustomException;
import kr.ac.phdljr.boardrefactor.global.exception.ErrorCode;
import kr.ac.phdljr.boardrefactor.global.exception.dto.response.CustomExceptionResponseDto;
import kr.ac.phdljr.boardrefactor.global.security.UserDetailsImpl;
import kr.ac.phdljr.boardrefactor.global.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    // 토큰 만료시간
    @Value("${custom.jwt.expiration-period}")
    private int tokenTime;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/v1/users/login");
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
                LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.email(),
                    requestDto.password(),
                    null
                )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        // 인증에 성공할 시, 응답 헤더에 토큰 추가
        String token = jwtUtil.createToken(username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        // 쿠키에도 추가
        Cookie cookie = new Cookie("accessToken", token.substring(7));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(tokenTime);
        response.addCookie(cookie);
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed
    ) {
        log.error(failed.getMessage());

        ErrorCode errorCode = ErrorCode.BAD_LOGIN;
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(CustomExceptionResponseDto.builder()
                .name(errorCode.name())
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .build()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}

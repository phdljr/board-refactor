package kr.ac.phdljr.boardrefactor.domain.email.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(timeToLive = 5 * 60)
public class EmailAuth {

    @Id
    private String email;

    private String code;

    private boolean check;

    @Builder
    public EmailAuth(final String email, final String code, final boolean check) {
        this.email = email;
        this.code = code;
        this.check = check;
    }
}

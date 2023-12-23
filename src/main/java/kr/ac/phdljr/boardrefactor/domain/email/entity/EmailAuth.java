package kr.ac.phdljr.boardrefactor.domain.email.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "auth", timeToLive = 5 * 60)
public class EmailAuth {

    @Id
    @Indexed
    private String email;

    private String code;

    private boolean check;

    @Builder
    public EmailAuth(final String email, final String code, final boolean check) {
        this.email = email;
        this.code = code;
        this.check = check;
    }

    public EmailAuth confirm() {
        this.check = true;
        return this;
    }
}

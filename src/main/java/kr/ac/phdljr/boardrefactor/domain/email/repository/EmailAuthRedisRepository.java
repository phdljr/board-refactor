package kr.ac.phdljr.boardrefactor.domain.email.repository;

import java.util.Optional;
import kr.ac.phdljr.boardrefactor.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRedisRepository extends CrudRepository<EmailAuth, Long> {

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Optional<EmailAuth> findByEmail(String email);

    Optional<EmailAuth> findByEmailAndCode(String email, String code);
}

package kr.ac.phdljr.boardrefactor.domain.user.repository;

import java.util.Optional;
import kr.ac.phdljr.boardrefactor.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

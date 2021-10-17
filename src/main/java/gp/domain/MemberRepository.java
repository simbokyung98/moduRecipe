package gp.domain;

import gp.web.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    @Modifying
    @Query(value = "update Members m set m.password = :password where m.id = :id", nativeQuery = true)
    void updatePwd(Long id, String password);
}

package gp.domain;

import gp.web.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {



    //List<Member> findByKeyword(String keyword);

    // 회원 정보 이름순
    @Query(value = "select * from member order by name asc",nativeQuery = true)
    List<Member> memberlist();



    // 회원 수
    @Query(value = "select count(*) from member",nativeQuery = true)
    List<Member> membercount();


    /*
    // 회원 검색
    @Query(value = "SELECT * FROM member WHERE username LIKE %:keyword% OR name LIKE %:keyword% OR date LIKE %:keyword%")
           //countQuery = "SELECT count(id) FROM member WHERE username LIKE %:keyword% OR name LIKE %:keyword% OR date LIKE %:keyword%"
    List<Member> findAllSearch(String keyword);


     */


    Member findByUsername(String username);

    @Modifying
    @Query(value = "update Members m set m.password = :password where m.id = :id", nativeQuery = true)
    void updatePwd(Long id, String password);
}

package capstonedesign.medicalproduct.repository.member;

import capstonedesign.medicalproduct.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

    @Query("SELECT m FROM Member m WHERE m.information.phoneNumber = :phoneNumber and m.information.name = :name")
    Optional<Member> findByNameAndPhoneNumber(@Param("name") String name, @Param("phoneNumber")String phoneNumber);
}


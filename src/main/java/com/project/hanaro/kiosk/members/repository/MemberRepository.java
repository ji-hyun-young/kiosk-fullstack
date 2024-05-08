package com.project.hanaro.kiosk.members.repository;

import com.project.hanaro.kiosk.members.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    @Query("SELECT m FROM members m WHERE m.deleteYn = false")
    Page<Member> findAll(Pageable pageable);

}

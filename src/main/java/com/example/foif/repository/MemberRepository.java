package com.example.foif.repository;

import com.example.foif.domain.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, String> {
}

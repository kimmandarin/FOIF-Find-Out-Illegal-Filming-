package com.example.foif.repository;

import com.example.foif.domain.FindPwd;
import com.example.foif.domain.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
}

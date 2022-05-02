package com.example.foif.service;

import com.example.foif.domain.Member;
import com.example.foif.domain.MemberDTO;
import com.example.foif.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class MemberService {

    MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository ){
        this.memberRepository = memberRepository;
    }

    @PersistenceContext
    EntityManager em;

    public void joinMember(MemberDTO memberDTO){
        memberRepository.save(memberDTO);
        em.persist(memberDTO);
    }
}

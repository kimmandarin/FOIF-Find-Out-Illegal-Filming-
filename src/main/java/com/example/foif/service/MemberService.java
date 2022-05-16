package com.example.foif.service;

import com.example.foif.domain.MemberDTO;
import com.example.foif.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class MemberService {

    MemberRepository memberRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    @PersistenceContext
    EntityManager em;

    public MemberDTO userInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;
        String username = ((User) principal).getUsername();

        List<MemberDTO> resultList = em.createQuery("SELECT m FROM MemberDTO m where m.policeId = :username", MemberDTO.class)
                .setParameter("username", username)
                .getResultList();

        return resultList.get(0);
    }

    public void findPassword(String username){
        List<MemberDTO> resultList = em.createQuery("SELECT m.password FROM MemberDTO m where m.policeId = :username")
                .setParameter("username", username)
                .getResultList();
        System.out.println(resultList);
    }

    public void joinMember(MemberDTO memberDTO){
        memberRepository.save(memberDTO);
        em.persist(memberDTO);
    }
}

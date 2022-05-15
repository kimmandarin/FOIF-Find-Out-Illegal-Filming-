package com.example.foif.service;

import com.example.foif.domain.Member;
import com.example.foif.domain.MemberDTO;
import com.example.foif.domain.Role;
import com.example.foif.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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

        List<MemberDTO> resultList = em.createQuery("SELECT m.email, m.policeStation, m.phoneNumber, m.policeId FROM MemberDTO m where m.policeId = :username")
                .setParameter("username", username)
                .getResultList();

        MemberDTO memberDTO = null;
        memberDTO.setEmail(String.valueOf(resultList.get(0).getEmail()));
        memberDTO.setPoliceId(String.valueOf(resultList.get(0).getPoliceId()));
        memberDTO.setPhoneNumber(String.valueOf(resultList.get(0).getPhoneNumber()));
        memberDTO.setPoliceStation(String.valueOf(resultList.get(0).getPoliceStation()));

        return memberDTO;
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

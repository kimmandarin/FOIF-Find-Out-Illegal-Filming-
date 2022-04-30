package com.example.foif.member;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
    @Override
    public String loginCheck(MemberDTO dto) {
        return dto.toString();
    }
}

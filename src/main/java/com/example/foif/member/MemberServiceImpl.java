package com.example.foif.member;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements MemberService {
    MemberDAO memberDAO;

    @Override
    public String loginCheck(MemberDTO dto, HttpSession session) {
        String name = memberDAO.loginCheck(dto);
        if(name != null){
            session.setAttribute("email", dto.getEmail());
            session.setAttribute("name", name);
        }
        return name;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
}

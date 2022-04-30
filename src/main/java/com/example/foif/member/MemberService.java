package com.example.foif.member;

import javax.servlet.http.HttpSession;

public interface MemberService {
    public String loginCheck(MemberDTO dto, HttpSession session);
    public void logout(HttpSession session);
}

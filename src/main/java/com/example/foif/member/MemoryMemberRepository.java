package com.example.foif.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
       // member.setUserName();
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}

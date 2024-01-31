package com.example.ordering.member.service;

import com.example.ordering.member.domain.Address;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.domain.Role;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepo;

    @Autowired
    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    public Member create(MemberCreateReqDto member) {

        Address address = new Address(
                member.getCity(),
                member.getStreet(),
                member.getZipcode()
        );

        Member newMember = Member.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .address(address)
                .role(Role.USER)
                .build();


        return memberRepo.save(newMember);

    }



}

package gp.service;

import gp.domain.Role;
import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;


    public Long joinUser(MemberDto memberDto) {

        return memberRepository.save(memberDto.toEntity()).getId();
    }








}
package gp.service;

import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Long joinUser(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }
}

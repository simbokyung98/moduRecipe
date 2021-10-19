package gp.service;

import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원가입
    public Long joinUser(MemberDto memberDto) {
        //validateDuplicateMember(memberDto);
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    public Optional<Member> idCheck(String username) {
        return memberRepository.findByUsername(username);
    }

    /*
    private void validateDuplicateMember(MemberDto memberDto) {
        memberRepository.findByUsername(memberDto.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("x");
                });
    }

     */


    // 로그인
    public MemberDto userLogin(MemberDto memberDto) {

        List<Member> memberList = memberRepository.findAll();

        for (Member temp : memberList) {
            if (temp.getUsername().equals(memberDto.getUsername()) && temp.getPassword().equals(memberDto.getPassword())) {
                MemberDto dto = new MemberDto();
                dto.setId(temp.getId());
                dto.setUsername(temp.getUsername());
                dto.setPassword(temp.getPassword());
                dto.setName(temp.getName());
                dto.setAddress(temp.getAddress());
                dto.setDate(temp.getDate());
                dto.setGender(temp.getGender());
                dto.setPhone(temp.getPhone());
                dto.setEmail(temp.getEmail());
                return dto;
            }

        }

        return null;
    }


    /*
    // 아이디 중복 확인
    public Optional<Member> idCheck(String username) {
        return memberRepository.findByUsername(username);
    }

     */


    public boolean checkUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

}

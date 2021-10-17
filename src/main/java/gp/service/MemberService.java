package gp.service;

import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원가입
    public Long joinUser(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }


    // 로그인
    public MemberDto userLogin(MemberDto memberDto){

        List<Member> memberList = memberRepository.findAll();

        for( Member temp : memberList){
            if(temp.getUsername().equals(memberDto.getUsername()) && temp.getPassword().equals(memberDto.getPassword())){
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





}

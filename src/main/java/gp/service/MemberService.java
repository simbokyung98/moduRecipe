package gp.service;



import gp.domain.Cart;
import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public MemberDto userLogin(MemberDto memberDto){

        List<Member> memberList = memberRepository.findAll();

        for( Member temp : memberList){
            if(temp.getUsername().equals(memberDto.getUsername()) && temp.getPassword().equals(memberDto.getPassword())){
                MemberDto dto = new MemberDto();
                dto.setId(temp.getId());
                dto.setUsername(temp.getUsername());
                dto.setPassword(temp.getPassword());
                dto.setName(temp.getName());
                dto.setEmail(temp.getEmail());

                return dto;
            }
        }
        return null;
    }

    @Transactional
    public List<MemberDto> getMemberList(){

        List<Member> memberList = memberRepository.findAll();
        List<MemberDto> memberDtoList=new ArrayList<>();

        for( Member member   : memberList){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .address(member.getAddress())
                    .date(member.getDate())
                    .gender(member.getGender())
                    .name(member.getName())
                    .password(member.getPassword())
                    .email(member.getEmail())
                    .username(member.getUsername())
                    .phone(member.getPhone())
                    .build();

            memberDtoList.add(memberDto);

            }
        return memberDtoList;
        }


        @Transactional
        public MemberDto getMember(Long id){
                Optional<Member> memberEntityWrapper = memberRepository.findById(id);
                Member member= memberEntityWrapper.get();

                MemberDto memberDto = MemberDto.builder()
                        .id(member.getId())
                        .address(member.getAddress())
                        .date(member.getDate())
                        .gender(member.getGender())
                        .name(member.getName())
                        .password(member.getPassword())
                        .email(member.getEmail())
                        .username(member.getUsername())
                        .phone(member.getPhone())
                        .build();

                return memberDto;

    }

        public Long saveMember(MemberDto memberDto){
            return memberRepository.save(memberDto.toEntity()).getId();
        }


        @Transactional
        public void memberDelete(Long id){
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member=optionalMember.get();

        memberRepository.delete(member);
        }

    @Transactional
    public void userUpdatePwd(Long id, String password) {

        memberRepository.updatePwd(id, password);
    }

    @Transactional
    public void deleteUser(Long id) {

        memberRepository.deleteById(id);
    }
}



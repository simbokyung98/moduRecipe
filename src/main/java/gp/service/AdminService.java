package gp.service;

import gp.domain.*;
import gp.web.dto.AdminDto;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;


    // 로그인 처리
    public AdminDto adminLogin(AdminDto adminDto){

        List<Admin> adminList = adminRepository.findAll();

        for( Admin temp : adminList){
            if(temp.getUsername().equals(adminDto.getUsername()) && temp.getPassword().equals(adminDto.getPassword())){
                AdminDto dto = new AdminDto();
                dto.setId(temp.getId());
                dto.setUsername(temp.getUsername());
                dto.setPassword(temp.getPassword());
                dto.setName(temp.getName());
                return dto;
            }

        }

        return null;
    }




    // 회원 관리
    public List<MemberDto> getMember(){
        List<Member> memberList = memberRepository.memberlist();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(Member member : memberList){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .name(member.getName())
                    .address(member.getAddress())
                    .date(member.getDate())
                    .gender(member.getGender())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .create_dated(member.getCreate_dated()).build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }


    // 회원수
    public List<MemberDto> getMembercount(){
        List<Member> memberList = memberRepository.membercount();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(Member member : memberList){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .name(member.getName())
                    .address(member.getAddress())
                    .date(member.getDate())
                    .gender(member.getGender())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .create_dated(member.getCreate_dated()).build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }









    /*

    // 회원 검색
    @Transactional
    public List<MemberDto> searchPosts(String keyword) {
        List<Member> memberList = memberRepository.findByKeyword(keyword);
        List<MemberDto> memberDtoList = new ArrayList<>();

        if(memberList.isEmpty()) return memberDtoList;

        for (Member member : memberList) {
            memberDtoList.add(this.convertEntityToDto(member));
        }
        return memberDtoList;
    }

    private MemberDto convertEntityToDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .name(member.getName())
                .address(member.getAddress())
                .date(member.getDate())
                .gender(member.getGender())
                .phone(member.getPhone())
                .email(member.getEmail())
                .create_dated(member.getCreate_dated()).build();


    }

     */





    // 회원 삭제
    @Transactional
    public void adminUserDelete(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.get();

        memberRepository.delete(member);
    }



}

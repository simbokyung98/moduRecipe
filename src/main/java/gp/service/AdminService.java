package gp.service;

import gp.domain.*;
import gp.web.dto.AdminDto;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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




    /*
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

     */

    //관리자 회원 정보 전체 조회
    @Transactional
    public Page<Member> pageGetAllMember(Pageable pageable){

        return memberRepository.findAll(pageable);
    }


    //관리자 회원 정보 검색
    public Page<Member> pageGetMemberSearch(String keyword, String searchType, Pageable pageable){

        if(searchType.equals("username")){
            return memberRepository.findByUsernameContaining(keyword, pageable);

        }else if(searchType.equals("name")){
            return memberRepository.findByNameContaining(keyword, pageable);

        }else if(searchType.equals("date")){
            return memberRepository.findByDateContaining(keyword, pageable);
        }
        return null;
    }





    //관리자 회원 정보 다음장 유무 확인
    @Transactional
    public Boolean getListCheck(Pageable pageable) {
        Page<Member> saved = pageGetAllMember(pageable);
        Boolean check = saved.hasNext();

        return check;
    }



    //관리자 회원 정보 카테고리 조회
    @Transactional
    public Page<Member> pageGetMainMemberList(String username, Pageable pageable){

        Page<Member> memberPage = memberRepository.findByUsername(username, pageable);


        return memberPage;
    }

    //관리자 회원 정보 페이지 다음장 유무 확인
    @Transactional
    public Boolean geCateListCheck(String username,Pageable pageable) {
        Page<Member> saved = pageGetMainMemberList(username,pageable);
        Boolean check = saved.hasNext();

        return check;
    }




    // 회원 삭제
    @Transactional
    public void adminUserDelete(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.get();

        memberRepository.delete(member);
    }



}

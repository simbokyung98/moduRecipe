package gp.service;


import gp.domain.Member;
import gp.domain.MemberRepository;
import gp.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> userEntityWrapper = memberRepository.findByUsername(username);
        Member userEntity = userEntityWrapper.get();


        //회원정보 권한에 따라서 권한을 부여한다.
        List<GrantedAuthority> auth = new ArrayList<>();
        if (("admin").equals(username)) {
            auth.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            auth.add(new SimpleGrantedAuthority("MEMBER"));
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), auth);
    }




}

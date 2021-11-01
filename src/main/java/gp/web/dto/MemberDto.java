package gp.web.dto;

import gp.domain.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String date;
    private String gender;
    private String phone;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_dated;






    @Builder
    public MemberDto(Long id, String username, String password, String name, String address, String date, String gender, String phone, String email, Date create_dated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.create_dated = create_dated;
    }

    public Member toEntity() {
        Member member = Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .address(address)
                .date(date)
                .gender(gender)
                .phone(phone)
                .email(email)
                .create_dated(create_dated)
                .build();
        return member;
    }
}

package gp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 40, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 45, nullable = false)
    private String date;

    @Column(length = 5, nullable = false)
    private String gender;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(name = "create_dated")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate create_dated;




    @Builder
    public Member(Long id, String username, String password, String name, String address, String date, String gender, String phone, String email, LocalDate create_dated) {
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



/*
    public boolean matchPassword(String password){
        if(password==null){
            return false;
        }

        return password.equals(password);

    }

 */
}

package gp.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = true)
    private String username;

    @Column(length = 30, nullable = true)
    private String password;

    @Column(length = 30, nullable = true)
    private String name;

    @Column(length = 50, nullable = true)
    private String address;

    @Column(length = 45, nullable = true)
    private String date;

    @Column(length = 5, nullable = true)
    private String gender;

    @Column(length = 15, nullable = true)
    private String phone;

    @Column(length = 50, nullable = true)
    private String email;

    public Long getId(){
        return id;
    }

    @Builder
    public Member(Long id, String username, String password, String name, String address, String date, String gender, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public boolean matchId(Long newId) {
        if ( newId == null ) {
            return false;
        }
        return newId.equals(getUsername());
    }

    public boolean matchPassword(String newPassword) {
        if ( newPassword == null ) {
            return false;
        }
        return newPassword.equals(password);
    }
    public void update(Member updateduser){
        this.password=updateduser.password;
    }
}

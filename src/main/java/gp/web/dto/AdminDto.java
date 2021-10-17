package gp.web.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminDto {

    private Long id;
    private String username;
    private String password;
    private String name;

    @Builder
    public AdminDto(Long id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

}

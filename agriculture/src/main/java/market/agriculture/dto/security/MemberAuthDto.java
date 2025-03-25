package market.agriculture.dto.security;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberAuthDto {

    private Long id;
    private String username;
    private String password;
    private String role;

    public MemberAuthDto(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

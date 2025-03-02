package market.agriculture.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.dto.JoinDto;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Slf4j
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    //    사용자 회원가입 로그인 아이디 비밀번호
    private String username;
    private String password;

    private String nickname;

    private Long balance;

    @Embedded
    private Address address;

    @Embedded
    private Phone phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Member() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static Member createMember(JoinDto joinDto, BCryptPasswordEncoder bCryptPasswordEncoder){

        Member member = new Member();
        member.setUsername(joinDto.getUsername());
        member.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        member.setRole(Role.valueOf(joinDto.getRole()));
        return member;
    }
    public static Member createMember(String username, Role role){

        Member member = new Member();
        member.setUsername(username);
        member.setRole(role);
        return member;
    }

}

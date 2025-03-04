package market.agriculture.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
//import market.agriculture.dto.member.JoinDto;
import market.agriculture.dto.member.JoinDto;
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
    @Column(name = "member_id")
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

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public Member() {
    }

    public static Member createMember(String username, Role role) {
        Member member = new Member();
        member.setUsername(username);
        member.setRole(role);
        return member;
    }
    public static Member createMemver(JoinDto joinDto,BCryptPasswordEncoder bCryptPasswordEncoder){
        Member member = new Member();
        member.setRole(Role.valueOf(joinDto.getRole()));
        member.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        member.setUsername(joinDto.getUsername());
        return member;
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


}

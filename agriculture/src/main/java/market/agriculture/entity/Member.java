package market.agriculture.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import market.agriculture.dto.JoinDto;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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

    //==생성 메서드==//
    public static Member createMember(String username, String password, String nickname, Address address, Phone phoneNumber, Role role) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setNickname(nickname);
        member.setAddress(address);
        member.setBalance(100000L);
        member.setRole(role);
        return member;
    }

    //==업데이트 메서드==//
    public void updateBasicInfo(String username, String nickname, Address address) {
        this.username = username;
        this.nickname = nickname;
        this.address = address;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    //==비즈니스 로직==//
    public boolean isSeller() {
        return getRole() == Role.Seller;
    }


}

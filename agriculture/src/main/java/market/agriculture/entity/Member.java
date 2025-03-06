package market.agriculture.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import market.agriculture.dto.member.JoinDto;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<>();

    public Member() {
    }

    public static Member createMemberForJWTFilter(String username, Role role) {
        Member member = new Member();
        member.setUsername(username);
        member.setRole(role);
        return member;
    }

    public static Member createMember(String username, String password, Address address, Role role, Phone phoneNumber, String nickname) {
        Member member = new Member();
        member.setUsername(username);
        member.setAddress(address);
        member.setPassword(password);
        member.setRole(role);
        member.setAddress(address);
        member.setPhoneNumber(phoneNumber);
        member.setNickname(nickname);
        return member;
    }

    //==비즈니스 로직==//
    public boolean isSeller() {
        return getRole() == Role.Seller;
    }


}

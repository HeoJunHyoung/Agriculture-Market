package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

@Entity @Getter
@Slf4j
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    //    사용자 회원가입 로그인 아이디 비밀번호
    private String username;
    private String password;

    private String nickname;

    @Embedded
    private Address address;

    @Embedded
    private Phone phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Seller() {
    }

}

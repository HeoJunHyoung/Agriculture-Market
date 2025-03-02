package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
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

    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Phone phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Seller() {
    }

    public Seller(Long id, String name, Address address, Phone phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}

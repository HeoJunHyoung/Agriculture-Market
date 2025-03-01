package market.agriculture.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

@Entity @Getter
@Slf4j
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private Long balance;

    @Embedded
    private Address address;

    @Embedded
    private Phone phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;



    public Customer() {
    }

    public Customer(Long id, String name, Long balance, Address address, Phone phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}

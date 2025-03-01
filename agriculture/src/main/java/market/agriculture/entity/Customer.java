package market.agriculture.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

@Entity @Getter @Setter
@Slf4j
//@DiscriminatorValue(value = "c")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String naverId;

    private Long balance;

    @Embedded
    private Address address;

    @Embedded
    private Phone phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;



    public Customer() {
    }

    public Customer(Long id, String name, String naverId, Long balance, Address address, Phone phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.naverId = naverId;
        this.balance = balance;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}

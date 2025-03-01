package market.agriculture.entity.embedded;

import jakarta.persistence.Embeddable;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Phone {

    private String phone1;
    private String phone2;

    public Phone() {
    }

    public Phone(String phone1, String phone2) {
        this.phone1 = phone1;
        this.phone2 = phone2;
    }
}

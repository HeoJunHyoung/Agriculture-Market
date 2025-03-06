package market.agriculture.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Embeddable
@Getter
public class Phone {

    private String phone1;
    private String phone2;

    protected Phone() {
    }

    public Phone(String phone1, String phone2) {
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }
}

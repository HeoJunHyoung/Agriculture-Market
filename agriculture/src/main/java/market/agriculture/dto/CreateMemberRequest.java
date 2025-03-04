package market.agriculture.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Member;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

@Getter @Setter
public class CreateMemberRequest {

    @NotEmpty
    @Size(min=8)
    private String username;

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password1;

    private String password2;

    @NotEmpty
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String nickname;

    private Role role;

    private Address address;

    private Phone phoneNumber;

    public boolean isPasswordMatch() {
        return this.password1.equals(this.password2);
    }

    public Member toEntity() {
        return Member.createMember(
                this.getUsername(),
                this.getPassword1(),
                this.getNickname(),
                this.getAddress(),
                this.getPhoneNumber(),
                this.getRole()
        );
    }

}

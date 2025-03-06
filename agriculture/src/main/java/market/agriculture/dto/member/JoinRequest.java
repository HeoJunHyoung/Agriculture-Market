package market.agriculture.dto.member;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Member;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

@Getter @Setter
public class JoinRequest {
    public JoinRequest() {
    }

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력하세요.")
//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\uD83C-\uDBFF\uDC00-\uDFFF]).{8,20}$",
//            message = "비밀번호는 영소문자, 숫자, 이모티콘을 최소 1개씩 포함해야 합니다."
//    )
    private String password1;

    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String password2;

    @Size(min = 2, max = 20)
    private String nickname;

    @NotNull(message = "역할(role)은 필수 입력 항목입니다.")
    private Role role;

    @NotNull(message = "주소 정보는 필수 입력 항목입니다.")
    private Address address;

    @NotNull(message = "전화번호 정보는 필수 입력 항목입니다.")
    private Phone phoneNumber;

    public boolean isPasswordEqual(){
        if(this.password1.equals(this.password2)){
            return true;
        }
        return false;
    }

    public Member toEntity() {

        Member member = Member.createMember(
                this.username,
                this.password1,
                this.address,
                this.role,
                this.phoneNumber,
                this.nickname
        );

        return member;
    }

}
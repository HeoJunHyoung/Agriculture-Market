package market.agriculture.dto.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateMemberPasswordRequest {

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @NotEmpty(message = "기존 비밀번호를 입력해주세요")
    private String password1;

    @NotEmpty(message = "기존 비밀번호를 입력해주세요")
    private String password2;

    @NotEmpty(message = "새 비밀번호를 입력해주세요")
    private String newPassword;

    public UpdateMemberPasswordRequest() {
    }
}

package market.agriculture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.enumerate.Role;

@Getter
@Setter
@Builder
public class UserDto {

    private Role role;
    private String name;
    private String oauthId;

}

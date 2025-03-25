package market.agriculture.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomMemberDetails implements UserDetails {

    private final MemberAuthDto memberAuthDto;

    public CustomMemberDetails(MemberAuthDto memberAuthDto) {
        this.memberAuthDto = memberAuthDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberAuthDto.getRole().toString();
            }
        });

        return collection;
    }

    public Long getId() {
        return memberAuthDto.getId();
    }

    @Override
    public String getPassword() {
        return memberAuthDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberAuthDto.getUsername();
    }
}

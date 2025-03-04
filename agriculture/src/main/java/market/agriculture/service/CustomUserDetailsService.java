package market.agriculture.service;

import market.agriculture.dto.CustomMemberDetails;
import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Member> member = memberRepository.findByUsername(username);

        if (member.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        else{
            return new CustomMemberDetails(member.get(0));
        }
    }
}

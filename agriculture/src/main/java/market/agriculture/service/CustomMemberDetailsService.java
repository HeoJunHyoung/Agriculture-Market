package market.agriculture.service;

import lombok.extern.slf4j.Slf4j;
import market.agriculture.dto.security.CustomMemberDetails;
import market.agriculture.dto.security.MemberAuthDto;
import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 커스텀된 LoginFilter에서 클라이언트의 요청에 대한 username과 password를 token이라는 DTO에 넣어서 authenticationManager에게 검증하라고 했었음.
 * ㄴ AuthenticationManager는 요청 데이터는 있지만, 실제 DB에서의 데이터와 맞는지 비교해야 하기 때문에 바로 검증이 불가능함.
 * ㄴ 그래서 내부적으로 CustomUserDetailsService가 DB로부터 회원 정보를 가져온 후, CustomUserDetails에 담아서 AuthenticationManager에게 전달하고,
 * ㄴ 최종적으로는 AUthenticationManager에서 검증을 수행
 */
@Slf4j
@Service
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomMemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 아래의 리턴값은 AuthenticationManager로 이동되었다가 최종적으로는 login 성공 여부를 확인하고, LoginFilter의 successful과 unsuccessful로 이동한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Member> findMembers = memberRepository.findByUsername(username);
        if (!findMembers.isEmpty()) {
            Member findMember = findMembers.get(0);
            System.out.println("Raw DB Password: [" + findMember.getPassword() + "]"); // 대괄호로 감싸서 출력
            //return new CustomMemberDetails(findMember.getId(), findMember.getUsername(), findMember.getPassword().trim(), findMember.getRole().toString());
            return new CustomMemberDetails(new MemberAuthDto(findMember.getId(), findMember.getUsername(), findMember.getPassword(), findMember.getRole().toString()));
        }
        throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
    }

}

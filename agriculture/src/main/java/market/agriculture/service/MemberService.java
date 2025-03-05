package market.agriculture.service;

import market.agriculture.dto.member.JoinDto;
import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public Long join(JoinDto joinDto) {

        if(joinDto.isPasswordEqual()) {

            joinDto.setPassword1(bCryptPasswordEncoder.encode(joinDto.getPassword1()));
            Member member = joinDto.toEntity();
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

        }
        throw new IllegalStateException();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUsername(member.getUsername());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}

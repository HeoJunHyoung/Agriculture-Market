//package market.agriculture.service;
//
////import market.agriculture.dto.member.JoinDto;
//import market.agriculture.entity.Member;
//import market.agriculture.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.memberRepository = memberRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Autowired
//
//    @Transactional
//    public Long join(JoinDto joinDto) {
//        validateDuplicateMember(joinDto);
//        Member member = Member.createMember(joinDto,bCryptPasswordEncoder);
//        memberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(JoinDto joinDto) {
//        List<Member> findMembers = memberRepository.findByUsername(joinDto.getUsername());
//        if (!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//
//}

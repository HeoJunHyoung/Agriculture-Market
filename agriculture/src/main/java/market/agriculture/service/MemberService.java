package market.agriculture.service;

//import market.agriculture.dto.JoinDto;
import market.agriculture.dto.member.CreateMemberRequest;
import market.agriculture.dto.member.UpdateMemberBasicRequest;
import market.agriculture.dto.member.UpdateMemberPasswordRequest;
import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(CreateMemberRequest request) {
        validateDuplicateMember(request);
        Member member = request.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    public void updateBasic(Long id, UpdateMemberBasicRequest request) {
        Member member = memberRepository.findById(id);
        member.updateBasicInfo(request.getUsername(), request.getNickname(), request.getAddress());
    }

    public void updatePassword(Long id, UpdateMemberPasswordRequest request) {
        Member member = memberRepository.findById(id);
        if (!isSamePassword(request.getPassword1(), request.getPassword2())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(request.getPassword1());
    }

    private boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }

    private void validateDuplicateMember(CreateMemberRequest request) {
        List<Member> findMembers = memberRepository.findByUsername(request.getUsername());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}

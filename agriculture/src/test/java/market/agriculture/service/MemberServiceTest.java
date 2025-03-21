package market.agriculture.service;

import market.agriculture.dto.member.CreateMemberRequest;
import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName(value = "정상 회원가입")
    public void makeAccount() throws Exception {
        CreateMemberRequest request = new CreateMemberRequest();
        request.setUsername("kim");
        request.setPassword1("1234");
        request.setPassword2("1234");

        Long savedMemberId = memberService.join(request);
        Member findMember = memberRepository.findById(savedMemberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        Assertions.assertThat(findMember).isEqualTo(memberRepository.findById(savedMemberId));
    }

//    @Test
//    @DisplayName(value = "중복 회원가입")
//    public void makeDuplicatedAccount() throws Exception {
//        Member member1 = new Member();
//        member1.setUsername("kim");
//        member1.setPassword("1234");
//
//        Member member2 = new Member();
//        member2.setUsername("kim");
//        member2.setPassword("4321");
//
//
//
//        memberService.join(member1);
//
//        Assertions.assertThatThrownBy(() -> memberService.join((member2))).isInstanceOf(IllegalStateException.class);
//    }

}
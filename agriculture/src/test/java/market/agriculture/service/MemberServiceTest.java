package market.agriculture.service;

import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
        Member member = new Member();
        member.setUsername("kim");
        member.setPassword("1234");

        memberService.join(member);
        Member savedMember = memberRepository.findById(member.getId());

        Assertions.assertThat(member).isEqualTo(memberRepository.findById(savedMember.getId()));
    }

    @Test
    @DisplayName(value = "중복 회원가입")
    public void makeDuplicatedAccount() throws Exception {
        Member member1 = new Member();
        member1.setUsername("kim");
        member1.setPassword("1234");

        Member member2 = new Member();
        member2.setUsername("kim");
        member2.setPassword("4321");



        memberService.join(member1);

        Assertions.assertThatThrownBy(() -> memberService.join((member2))).isInstanceOf(IllegalStateException.class);
    }

}
package market.agriculture.service;

import market.agriculture.entity.Member;
import market.agriculture.repository.MemberRepository;
import org.assertj.core.api.Assertions;
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
    MemberRepository customerRepository;

    /**
     * 중복 nickname 회원가입 검증
     */
    @Test
    public void makeAccount() throws Exception {
        Member member1 = Member.builder()
                .nickname("kim")
                .build();
        Member member2 = Member.builder()
                .nickname("kim")
                .build();

        memberService.join(member1);

        Assertions.assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }

}
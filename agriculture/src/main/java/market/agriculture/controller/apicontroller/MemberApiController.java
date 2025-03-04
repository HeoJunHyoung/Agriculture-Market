package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.CreateMemberRequest;
import market.agriculture.dto.CreateMemberResponse;
import market.agriculture.entity.Member;
import market.agriculture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @Autowired
    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * V1. memberService.join(request)를 넘기고, MemberService 내부에 toEntity()라는 메서드를 통해 DTO를 Entity 승격하는 메서드를 작성
     * V2. DTO를 Entity로 승격하기 위한 메서드를 DTO 내부에 작성
     *     ㄴ 이전 V1에서는 Service 계층이 비즈니스 로직 뿐만 아니라 엔티티 승격이라는 로직도 포함하기 때문에 SRP 위반 가능성
     */
    @PostMapping("/join")
    public void join(@RequestBody @Valid CreateMemberRequest request) {
        if (!request.isPasswordMatch()) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        Member member = request.toEntity();
        memberService.join(member);
    }



}

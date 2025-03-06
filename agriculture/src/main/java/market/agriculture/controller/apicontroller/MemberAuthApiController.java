package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.member.CreateMemberRequest;
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
public class MemberAuthApiController {

    private final MemberService memberService;

    @Autowired
    public MemberAuthApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * V1. memberService.join(request)를 넘기고, MemberService 내부에 toEntity()라는 메서드를 통해 DTO를 Entity 승격하는 메서드를 작성
     * V2. DTO를 Entity로 승격하기 위한 메서드를 DTO 내부에 작성
     *     ㄴ 이전 V1에서는 Service 계층이 비즈니스 로직 뿐만 아니라 엔티티 승격이라는 로직도 포함하기 때문에 SRP 위반 가능성
     * V3. DTO를 Entity로 승격시키는 메서드를 DTO 내부에 작성하는 것은 동일 / 다만, 승격 메서드 사용시점을 Service로 위임하여, Controller는 단순히 request를 넘김
     */
    @PostMapping("/join")
    public void join(@RequestBody @Valid CreateMemberRequest request) {
        if (!request.isPasswordMatch()) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        Long memberId = memberService.join(request);
    }



}

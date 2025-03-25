package market.agriculture.controller.apicontroller;


import jakarta.validation.Valid;
import market.agriculture.dto.member.UpdateMemberBasicRequest;
import market.agriculture.dto.member.UpdateMemberPasswordRequest;
import market.agriculture.dto.security.CustomMemberDetails;
import market.agriculture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberUpdateApiController {

    private final MemberService memberService;

    @Autowired
    public MemberUpdateApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/update")
    public void updateMemberBasic(@AuthenticationPrincipal CustomMemberDetails memberDetails, @RequestBody @Valid UpdateMemberBasicRequest request) {
        memberService.updateBasic(memberDetails.getId(), request);
    }

    @PostMapping("/update/password")
    public void updateMemberPassword(@AuthenticationPrincipal CustomMemberDetails memberDetails, @RequestBody @Valid UpdateMemberPasswordRequest request) {
        memberService.updatePassword(memberDetails.getId(), request);
    }

}

package market.agriculture.controller.apicontroller;


import jakarta.validation.Valid;
import market.agriculture.dto.member.UpdateMemberBasicRequest;
import market.agriculture.dto.member.UpdateMemberPasswordRequest;
import market.agriculture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberUpdateApiController {

    private final MemberService memberService;

    @Autowired
    public MemberUpdateApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/update/{id}")
    public void updateMemberBasic(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberBasicRequest request) {
        memberService.updateBasic(id, request);
    }

    @PostMapping("/update/password/{id}")
    public void updateMemberPassword(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberPasswordRequest request) {
        memberService.updatePassword(id, request);
    }

}

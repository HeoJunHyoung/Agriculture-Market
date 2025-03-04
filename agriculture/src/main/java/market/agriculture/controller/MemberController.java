package market.agriculture.controller;

import market.agriculture.dto.member.JoinDto;
import market.agriculture.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 관련 API를 제공하는 컨트롤러이다.
 *
 * 회원가입 요청 처리를 담당한다. 로그인 요청 처리는 LoginFilter에 존재한다.
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String test(){return "root page";}
    @GetMapping("/login")
    public String login(){
        return "login Page";
    }

    /**
     *
     * @param joinDto
     * @apiNote 실패시 에러 message혹은 validaiton message 전송되도록 작성이 필요하다. 회원가입 요청이다.
     */
    @PostMapping("/join")
    public void join(JoinDto joinDto) {

        memberService.join(joinDto);

    }

}

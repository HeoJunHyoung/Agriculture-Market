package market.agriculture.controller;

import market.agriculture.dto.JoinDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/member")
public class MemberController {


    @GetMapping("/login")
    public String loginPage(){
        return "login page";
    }

    @GetMapping("/join")
    public ResponseEntity<String> joinPage() {
        return ResponseEntity.ok("<html><body><h1>Join Page</h1></body></html>");
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        return "회원가입 됨";

    }

}

package market.agriculture.controller;

import market.agriculture.dto.JoinDto;
import market.agriculture.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class accountController {

    private final AccountService accountService;

    @Autowired
    public accountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/test")
    public String main(){
        return "test";
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginPage(){
        return ResponseEntity.ok("<html><body><h1>Login Page</h1></body></html>");
    }

    @GetMapping("/join")
    public ResponseEntity<String> joinPage() {
        return ResponseEntity.ok("<html><body><h1>Join Page</h1></body></html>");
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {

        accountService.join(joinDto);
        return "회원가입 됨";

    }
}

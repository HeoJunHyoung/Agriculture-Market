package market.agriculture.controller.apicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/list")
    public List<String> getChatList(){
        return null;
    }

    @PostMapping("/create")
    public String createChat(){
        return null;
    }

}

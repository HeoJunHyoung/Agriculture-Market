package market.agriculture.controller;

import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import market.agriculture.dto.chat.ChatDto;
import market.agriculture.dto.chat.OneToOneChatDto;
import market.agriculture.repository.MemberRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 채팅 관련 API를 제공하는 컨트롤러이다.
 *
 * 앱 사용자의 연결되어 있는 채팅목록 전체를 가져오는 처리를 한다.
 * 게시글에서 판매자와 채팅을 생성할 수 있는 요청을 처리한다.
 * 단일 채팅창에 대해 채팅정보를 가져오는 처리를 한다.
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

//    private final JwtUtil jwtUtil;
//    private final MemberService memberservice
//    private final ChatService chatService;


    /**
     *
     * @param request
     * @return List<ChatDto>
     * @apiNote 현재 member와 연결되어 있는 채팅 목록을 가져오는 요청이다.
     */
    @GetMapping("/list")
    public List<ChatDto> getChatList(HttpServletRequest request ){

        String accessToken= request.getHeader("Authorization");
//        String username = jwtutil.getUsername(accessToken);
//        Member member = memberservice.findByUsername(username);


        return null;
    }

    /**
     *
     * @param seller_member_id
     * @param request
     * @return redirect:/chat/connect/{chat_id}
     * @apiNote 채팅 생성을 요청한 member와 게시글의 판매자 member 두 유저를 위한 채팅창을 개설한 후, 채팅 데이터 조회 api를 redirect한다.
     */
    @PostMapping("/create/{seller_member_id}")
    public String createChat(@PathVariable Long seller_member_id, HttpServletRequest request){

        String accessToken= request.getHeader("Authorization");
//        String username = jwtutil.getUsername(accessToken);
//        Member customer = memberRepository.findByUsername(username);
//        Member seller = memberService.findByMemberId(seller_member_id);
//        Chat chat = chatService.createChat(customer,seller)

        return null;
    }

    /**
     *
     * @param chat_id
     * @return OneToOneChatDto : chat_id, List<String> 채팅 목록을 담는 dto를 반환한다.
     * @apiNote chat_id를 기준으로 해당 채팅의 전체 데이터를 조회하여 반환한다.
     */
    @GetMapping("/connect/{chat_id}")
    public OneToOneChatDto connectChat(@PathVariable Long chat_id){

        return null;

    }

}

package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.post.CreatePostRequest;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.embedded.Address;
import market.agriculture.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostApiController {

    private final PostService postService;

    @Autowired
    public PostApiController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/upload")
    public void uploadPost(@RequestBody @Valid CreatePostRequest request, @RequestParam(value = "memberId") Long memberId) {
        postService.createPostWithItems(request, memberId);
    }


}

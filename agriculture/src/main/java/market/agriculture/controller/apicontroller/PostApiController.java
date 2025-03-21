package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.post.CheckPostDetailsResponse;
import market.agriculture.dto.post.CheckPostResponse;
import market.agriculture.dto.post.CreatePostRequest;
import market.agriculture.dto.post.UpdatePostRequest;
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
    public void upload(@RequestBody @Valid CreatePostRequest request, @RequestParam(value = "memberId") Long memberId) {
        postService.createPostWithItems(request, memberId);
    }

    @PostMapping("/modify/{postId}")
    public void update(@PathVariable(value="postId") Long postId, @RequestBody @Valid UpdatePostRequest request) {
        postService.updatePostWithItems(postId, request);
    }


    @PostMapping("/list")
    public List<CheckPostResponse> checkPosts(@RequestParam(value = "memberId") Long memberId) {
        return postService.findMyPosts(memberId);
    }

    @PostMapping("/list/{postId}")
    public CheckPostDetailsResponse checkPostDetails(@PathVariable(value="postId") Long postId) {
        return postService.findMyPostDetails(postId);
    }

}

package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.post.CheckPostDetailsResponse;
import market.agriculture.dto.post.CheckPostResponse;
import market.agriculture.dto.post.CreatePostRequest;
import market.agriculture.dto.post.UpdatePostRequest;
import market.agriculture.dto.security.CustomMemberDetails;
import market.agriculture.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void uploadPost(@AuthenticationPrincipal CustomMemberDetails memberDetails, @RequestBody @Valid CreatePostRequest request) {
        postService.createPostWithItems(request, memberDetails.getId());
    }

    @PostMapping("/modify/{postId}")
    public void updatePost(@PathVariable(value="postId") Long postId, @RequestBody @Valid UpdatePostRequest request) {
        postService.updatePostWithItems(postId, request);
    }


    @GetMapping("/list")
    public List<CheckPostResponse> checkPosts(@AuthenticationPrincipal CustomMemberDetails memberDetails) {
        return postService.findMyPosts(memberDetails.getId());
    }

    @GetMapping("/list/{postId}")
    public CheckPostDetailsResponse checkPostDetails(@PathVariable(value="postId") Long postId) {
        return postService.findMyPostDetails(postId);
    }

}

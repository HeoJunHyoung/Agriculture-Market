package market.agriculture.controller;

import jakarta.validation.Valid;
import market.agriculture.dto.CustomMemberDetails;
import market.agriculture.dto.post.PostDetailsResponse;
import market.agriculture.dto.post.PostListResponse;
import market.agriculture.dto.post.PostUploadRequest;
import market.agriculture.dto.post.ReviewUploadReqeust;
import market.agriculture.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 게시글 관련 요청을 처리하는 컨트롤러이다.
 *
 * 게시글을 등록한다.
 * 게시글을 수정한다.
 * 게시글을 삭제한다.
 * 게시글의 상세 정보와 게시글의 리뷰정보를 보여준다.
 * 게시글에 리뷰를 남긴다.
 * 게시글을 랜덤기준으로 조회한다.
 * 게시글을 제목기준으로 조회한다.
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     *
     * @param postUploadRequest
     * @param customMemberDetails
     * @apiNote 게시판 입력값을 받아 게시글을 등록한다.
     */
    @PostMapping("/upload")
    public void uploadPost(@Valid @RequestBody PostUploadRequest postUploadRequest, @AuthenticationPrincipal CustomMemberDetails customMemberDetails){

        postService.createPostWithItems(customMemberDetails.getUsername(), postUploadRequest);

    }

    /**
     *
     * @param customMemberDetails
     * @param postUploadRequest
     * @param postId
     * @apiNote 게시판 입력값의 수정사항을 받아 게시글을 등록한다.
     */
    @PostMapping("/modify/{postId}")
    public void modifyPost( @AuthenticationPrincipal CustomMemberDetails customMemberDetails,
                            @Valid @RequestBody PostUploadRequest postUploadRequest, @PathVariable("postId") Long postId){


        postService.updatePostWithItems(customMemberDetails.getUsername(),postId,postUploadRequest);

    }

    /**
     *
     * @param customMemberDetails
     * @param postId
     * @apiNote 삭제 요청자가 게시글의 생성자인지 확인 후 삭제한다.
     */
    @DeleteMapping("/delete/{postId}")
    public void deletePost(@AuthenticationPrincipal CustomMemberDetails customMemberDetails, @PathVariable("postId") Long postId){

        postService.deletePostWithItems(customMemberDetails.getUsername(),postId);
        //리뷰 삭제도 진행해야함.

    }


    /**
     *
     * @param postId
     * @return PostInfoDto : 게시글의 정보, 판매자의 정보, 리뷰
     * @apiNote 게시글 하나에 대한 모든 정보를 보여주기 위한 요청이다.
     */
    @GetMapping("/details/{postId}")
    public PostDetailsResponse getOnePost(@PathVariable("postId") Long postId){

        PostDetailsResponse postDetailsResponse = postService.getOneDetailPostWithReview(postId);

        return postDetailsResponse;

    }


    /**
     *
     * @param customMemberDetails
     * @param reviewUploadReqeust
     * @param postId
     * @apiNote 리뷰 정보를 받아 저장한다.
     */
    @PostMapping("/review/{postId}")
    public void createReview(@AuthenticationPrincipal CustomMemberDetails customMemberDetails,
                             @RequestBody ReviewUploadReqeust reviewUploadReqeust, @PathVariable("postId") Long postId){

        postService.createReview(customMemberDetails.getUsername(),reviewUploadReqeust,postId);

    }

    /**
     *
     * @param title
     * @return PostListResPonseDto : 전달한 게시글 데이터들을 받아오는 dto이다.
     * @apiNote 제목 게시글 검색을 위한 요청이다.
     */
    @GetMapping("/search/{title}")
    public PostListResponse searchByTitle(@AuthenticationPrincipal CustomMemberDetails customMemberDetails,@PathVariable String title){
 

        return null;
    }

    /**
     *
     * @return PostListResPonseDto : 전달한 게시글 데이터들을 받아오는 dto이다.
     * @apiNote 랜덤 게시글 검색을 위한 요청이다.
     */
    @GetMapping("/search/random")
    public PostListResponse searchByRandom(){

        return null;
    }
}

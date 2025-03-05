package market.agriculture.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import market.agriculture.dto.CustomMemberDetails;
import market.agriculture.dto.post.PostModifyDto;
import market.agriculture.dto.post.PostInfoDto;
import market.agriculture.dto.post.PostListResPonseDto;
import market.agriculture.dto.post.PostUploadDto;
import market.agriculture.dto.post.ReviewUploadDto;
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
     * @param postUploadDto
     * @param customMemberDetails
     * @apiNote 게시판 입력값을 받아 게시글을 등록한다.
     */
    @PostMapping("/upload")
    public void uploadPost(@Valid @RequestBody PostUploadDto postUploadDto, @AuthenticationPrincipal CustomMemberDetails customMemberDetails){

        postService.createPostWithItems(customMemberDetails.getUsername(),postUploadDto);

    }

    /**
     *
     * @param postModifyDto
     * @param customMemberDetails
     * @apiNote 게시판 입력값의 수정사항을 받아 게시글을 등록한다.
     */
    @PostMapping("/modify")
    public void modifyPost(@Valid @RequestBody PostModifyDto postModifyDto, @AuthenticationPrincipal CustomMemberDetails customMemberDetails){



    }

    /**
     *
     * @param request
     * @param post_id
     * @apiNote 삭제 요청자가 게시글의 생성자인지 확인 후 삭제한다.
     */
    @GetMapping("/delete/{post_id}")
    public void deletePost(HttpServletRequest request, @PathVariable Long post_id){

    }


    /**
     *
     * @param post_id
     * @return PostInfoDto : 게시글의 정보, 판매자의 정보, 리뷰
     * @apiNote 게시글 하나에 대한 모든 정보를 보여주기 위한 요청이다.
     */
    @GetMapping("/details/{post_id}")
    public PostInfoDto getOnePost(@PathVariable Long post_id){
//        여기에 리뷰글들도 다 긁어서 보여줘야 하고,

        return null;
    }


    /**
     *
     * @param request
     * @param reviewUploadDto
     * @apiNote 리뷰 정보를 받아 저장한다.
     */
    @PostMapping("/review")
    public void createReview(HttpServletRequest request, ReviewUploadDto reviewUploadDto){

    }

    /**
     *
     * @param title
     * @return PostListResPonseDto : 전달한 게시글 데이터들을 받아오는 dto이다.
     * @apiNote 제목 게시글 검색을 위한 요청이다.
     */
    @GetMapping("/search/{title}")
    public PostListResPonseDto searchByTitle(@PathVariable String title){

        return null;
    }

    /**
     *
     * @return PostListResPonseDto : 전달한 게시글 데이터들을 받아오는 dto이다.
     * @apiNote 랜덤 게시글 검색을 위한 요청이다.
     */
    @GetMapping("/search/random")
    public PostListResPonseDto searchByRandom(){

        return null;
    }
}

package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.review.ReviewRequest;
import market.agriculture.dto.review.ReviewResponse;
import market.agriculture.dto.security.CustomMemberDetails;
import market.agriculture.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewApiController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/show")
    public List<ReviewResponse> showReviews(@RequestParam(value = "postId") Long postId) {
        return reviewService.getReviews(postId);
    }

    @PostMapping("/comment")
    public void comment(@RequestParam(value = "postId") Long postId, @AuthenticationPrincipal CustomMemberDetails memberDetails, @RequestBody @Valid ReviewRequest request) {
        reviewService.comment(postId, memberDetails.getId(), request);
    }


}

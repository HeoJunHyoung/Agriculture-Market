package market.agriculture.service;

import market.agriculture.dto.review.ReviewRequest;
import market.agriculture.dto.review.ReviewResponse;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.Review;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.PostRepository;
import market.agriculture.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public List<ReviewResponse> getReviews(Long postId) {
        Post post = postRepository.findReviewsByPostId(postId);
        List<Review> reviews = post.getReviews();
        return reviews.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void comment(Long postId, Long memberId, ReviewRequest request) {
        Post post = postRepository.findById(postId);
        Member member = memberRepository.findById(memberId);
        Review review = request.toEntity(member, post);

        reviewRepository.save(review);
    }

}

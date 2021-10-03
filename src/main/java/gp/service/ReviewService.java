package gp.service;

import gp.domain.Review;
import gp.domain.ReviewRepository;
import gp.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired private ReviewRepository reviewRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

    public Long savereview(ReviewDto reviewDto){
        return reviewRepository.save(reviewDto.toEntity()).getReviewkey();
    }

    @Transactional
    public ReviewDto getReview(Long recipekey){
        Optional<Review> reviewWrapper = reviewRepository.findById(recipekey);
        Review review=reviewWrapper.get();

        ReviewDto reviewDto=ReviewDto.builder()
                .reviewkey(review.getReviewkey())
                .reviewcontent(review.getReviewcontent())
                .reviewupdated(review.getReviewupdated())
                .member(review.getMember())
                .recipe(review.getRecipe())
                .build();
        return reviewDto;
    }

    private ReviewDto convertEntityToDto(Review review){
        return  ReviewDto.builder()
                .reviewkey(review.getReviewkey())
                .reviewcontent(review.getReviewcontent())
                .reviewupdated(review.getReviewupdated())
                .member(review.getMember())
                .recipe(review.getRecipe())
                .build();
    }

    public List<ReviewDto> getrivewlist(Integer pageNum){
        Page<Review> page= reviewRepository.findAll(PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC,"recipekey")));

        List<Review> reviews=page.getContent();
        List<ReviewDto> reviewDtoList=new ArrayList<>();

        for(Review review : reviews){
            reviewDtoList.add(this.convertEntityToDto(review));
        }
        return reviewDtoList;
    }
}

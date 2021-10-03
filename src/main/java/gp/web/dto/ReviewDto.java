package gp.web.dto;

import gp.domain.Member;
import gp.domain.Recipe;
import gp.domain.Review;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private Long reviewkey;
    private String reviewcontent;
    private Date reviewupdated;
    private Recipe recipe;
    private Member member;

    @Builder
    public ReviewDto(Long reviewkey, String reviewcontent, Date reviewupdated,Recipe recipe, Member member)
    {
        this.reviewkey=reviewkey;
        this.reviewcontent=reviewcontent;
        this.reviewupdated=reviewupdated;
        this.recipe=recipe;
        this.member=member;
    }

    public Review toEntity(){
        return Review.builder()
                .reviewkey(reviewkey)
                .reviewcontent(reviewcontent)
                .reviewupdated(reviewupdated)
                .recipe(recipe)
                .member(member)
                .build();

    }
}

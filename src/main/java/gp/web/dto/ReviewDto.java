package gp.web.dto;

import gp.domain.Recipe;
import gp.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private Long reviewkey;
    private String reviewcontent;
    private Date reviewupdated;
    private Recipe recipe;
    private String reviewwriter;

    @Builder
    public ReviewDto(Long reviewkey, String reviewcontent, Date reviewupdated, Recipe recipe, String reviewwriter)
    {
        this.reviewkey=reviewkey;
        this.reviewcontent=reviewcontent;
        this.reviewupdated=reviewupdated;
        this.recipe=recipe;
        this.reviewwriter=reviewwriter;
    }

    public Review toEntity(){
        return Review.builder()
                .reviewkey(reviewkey)
                .reviewcontent(reviewcontent)
                .reviewupdated(reviewupdated)
                .recipe(recipe)
                .reviewwriter(reviewwriter)
                .build();

    }
}

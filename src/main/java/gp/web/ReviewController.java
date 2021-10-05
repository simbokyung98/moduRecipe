package gp.web;


import gp.domain.Recipe;
import gp.domain.RecipeRepository;
import gp.domain.Review;
import gp.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    ReviewRepository reviewRepository;

    /*@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/recipedetail/{recipekey}/review")
    public List<Review> getRecipeReview(@PathVariable Long recipekey){
        Recipe recipe = recipeRepository.findById(recipekey).get();
        return ;
    }*/

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/recipedetail/{recipekey}/review")
    public Review createReview(@PathVariable Long recipekey, @RequestBody Review review){
        Optional<Recipe> recipeItem= recipeRepository.findById(recipekey);
        review.setRecipe(recipeItem.get());
        reviewRepository.save(review);
        return review;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/recipedetail/{recipekey}/review/{reviewkey}")
    public Review updateReview(@PathVariable Long recipekey,@PathVariable Long reviewkey,@RequestBody Review review){
        Optional<Recipe> recipeItem= recipeRepository.findById(recipekey);
        review.setRecipe(recipeItem.get());
        Review newReview = reviewRepository.findById(reviewkey).get();
        newReview.setReviewcontent(review.getReviewcontent());
        newReview.setReviewwriter(review.getReviewwriter());
        return newReview;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/recipedetail/{recipekey}/review/{reviewkey}")
    public String deleteComment(@PathVariable Long reviewkey, @PathVariable Long recipekey){
        reviewRepository.deleteById(reviewkey);
        return "리뷰삭제완료!";
    }
}

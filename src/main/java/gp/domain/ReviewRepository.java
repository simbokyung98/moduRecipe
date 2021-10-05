package gp.domain;

import gp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    public Review findReviewsByRecipe(Long recipekey);
}

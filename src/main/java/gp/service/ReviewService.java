package gp.service;

import gp.domain.*;
import gp.web.dto.MemberDto;
import gp.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired private final ReviewRepository reviewRepository;

    @Autowired private final RecipeRepository recipeRepository;



   /* @Transactional
    public void reviewsave(Long recipekey, Review review){
        Recipe recipe = recipeRepository.findById(recipekey).orElseThrow(()->new IllegalArgumentException("해당 boardId가 없습니다. id=" + recipekey));

        review.save(recipe);
        reviewRepository.save(review);


    }*/

}

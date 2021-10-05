package gp.web;

import gp.domain.Recipe;
import gp.domain.Review;
import gp.service.RecipeService;
import gp.service.ReviewService;
import gp.web.dto.RecipeDto;
import gp.web.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);

        model.addAttribute("recipelist", recipeDtoList);

        return "index";
    }

    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetail(@PathVariable("recipekey")Long recipekey, Model model){
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);




        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);
        return "recipedetail.html";
    }
    /*@RequiredArgsConstructor
    @RestController
    public class ReviewController {
        private final ReviewService reviewService;

        @PostMapping("/recipedetail/{recipekey}")
        public void save(@PathVariable Long recipekey,
                         @RequestBody Review review)
        { reviewService.reviewsave(recipekey,review); }
    }*/


}

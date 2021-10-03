package gp.web;

import gp.domain.Recipe;
import gp.service.RecipeService;
import gp.service.ReviewService;
import gp.web.dto.RecipeDto;
import gp.web.dto.ReviewDto;
import lombok.AllArgsConstructor;
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
    public String recipedetail(@PathVariable("recipekey")Long recipekey,@PathVariable("reviewkey")Long reviewkey, Model model){
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);
        ReviewDto reviewDto = reviewService.getReview(reviewkey);


        model.addAttribute("reviewDto",reviewDto);
        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);
        return "recipedetail.html";
    }
    @PostMapping("/recipedetail/{recipekey}")
    public String reviewwrite(@ModelAttribute ReviewDto reviewDto){
        reviewService.savereview(reviewDto);
        return ("redirect:/reviewdetail/{recipekey}");
    }
}

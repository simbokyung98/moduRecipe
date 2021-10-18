package gp.web;

import gp.domain.Material;
import gp.domain.Recipe;
import gp.domain.Review;
import gp.service.MaterialService;
import gp.service.RecipeService;
import gp.service.ReviewService;
import gp.web.dto.MaterialDto;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import gp.web.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/")
    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);

        model.addAttribute("recipelist", recipeDtoList);

        return "index";
    }
    @GetMapping("/best")
    public String bestrecipe(Model model){
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe();
        model.addAttribute("bestlist",recipeDtoList);
        return "best";
    }


    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetail(@PathVariable("recipekey")Long recipekey, Model model, HttpSession session){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);

        String materialStr = recipeDto.getRecipemateriallist();

        if(! StringUtils.isEmpty(materialStr)){
            String[] materialList = materialStr.split(",");

            List<Material> materialDtoList = materialService.getMaterialByTitles(materialList);

            model.addAttribute("materialDtoList",materialDtoList);

        }

        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);
        return "recipedetail";
    }



}

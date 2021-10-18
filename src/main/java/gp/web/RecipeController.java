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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

//    @GetMapping("/")
//    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
//        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);
//
//        model.addAttribute("recipelist", recipeDtoList);
//
//        return "index";
//    }
    @GetMapping("/best")
    public String bestrecipe(Model model){
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe();
        model.addAttribute("bestlist",recipeDtoList);
        return "best";
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

    //관리자 레시피 페이지(전체) 이동
    @GetMapping("/adminContent")
    public String adminContent(Model model, @PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable){

        Page<Recipe> recipePage =recipeService.pageGetAllContent(pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "레시피");

        return "adminContent";
    }
    //관리자 레시피 페이지(카테고리) 이동
    @GetMapping("/adminCotentCate/{recipetype}")
    public String adminCotentCate (@PathVariable("recipetype") String recipetype, Model model,@PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable ){
        Page<Recipe> recipePage = recipeService.pageGetRecipeCate(recipetype, pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "레시피");

        return "adminContent";

    }


    //관리자 레시피 추가 페이지 이동
    @GetMapping("/adminContentAdd")
    public String adminConAdd(){
        return "adminContentAdd";
    }

    //관리자 레시피 추가 액션
    @PostMapping("/adminContentAddAction")
    public String adminConAddAction(RecipeDto recipeDto){
        recipeService.adminContentAdd(recipeDto);

        return "redirect:/adminContent";

    }
    //관리자 레시피 수정 페이지 이동
    @GetMapping("/adminContentUpdate/{recipekey}")
    public String adminContentUpdate(@PathVariable("recipekey") Long recipekey, Model model){

        RecipeDto recipeDto = recipeService.getRecipeUp(recipekey);

        model.addAttribute("recipedetail", recipeDto);

        return "adminContentUpdate";

    }

    //관리자 레시피 수정 액션
    @PostMapping("/recipeUpdate/{recipekey}")
    public String adminRecipeUpdate(@PathVariable("recipekey") Long recipekey, RecipeDto recipeDto){
        recipeService.recipeUpdate(recipekey, recipeDto);

        return "redirect:/adminContent";
    }

    //관리자 레시피 검색
    @GetMapping("/recipesearch")
    public String adminRecipeSearch(@RequestParam(value="keyword") String keyword, @RequestParam(value="select") String select, Model model,
                                    @PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable){
        Page<Recipe> recipePage = recipeService.pageGetRecipeSearch(keyword,select, pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "레시피");

        return "adminContent";
    }

    //관리자 레시피 삭제
    @RequestMapping("/adminContentDelete/{recipekey}")
    public String adminRecipeDelete(@PathVariable("recipekey") Long recipekey){

        recipeService.adminRecipeDelete(recipekey);

        return "redirect:/adminContent";

    }

}

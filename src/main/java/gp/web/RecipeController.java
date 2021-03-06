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


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


//    @GetMapping("/")
//    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
//        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);
//
//        model.addAttribute("recipelist", recipeDtoList);
//
//        return "index";
//    }

    @Autowired
    private MaterialService materialService;

    @GetMapping("/")
    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);

        model.addAttribute("recipelist", recipeDtoList);

        return "index";
    }

    // recipe(creator)
    @GetMapping("/recipelist/{recipecreator}")
    public String creatorRecipe(@PathVariable("recipecreator") String recipecreator, Model model){
        List<RecipeDto> recipeDtoList = recipeService.getcreatorbestRecipe(recipecreator);
        List<RecipeDto> recipeDtoList1 = recipeService.getcreatornewRecipe(recipecreator);
        model.addAttribute("recipelist",recipeDtoList);
        model.addAttribute("recipelist",recipeDtoList1);
        return "recipe";
    }

    // best

    @GetMapping("/best")
    public String bestrecipe(Model model){
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe();
        model.addAttribute("bestlist",recipeDtoList);
        return "best";
    }


    //????????? ????????? ?????????(??????) ??????
    @GetMapping("/adminContent")
    public String adminContent(Model model, @PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable){

        Page<Recipe> recipePage =recipeService.pageGetAllContent(pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar ?????? ??????
        model.addAttribute("adminmenu", "?????????");

        return "adminContent";
    }
    //????????? ????????? ?????????(????????????) ??????
    @GetMapping("/adminCotentCate/{recipetype}")
    public String adminCotentCate (@PathVariable("recipetype") String recipetype, Model model,@PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable ){
        Page<Recipe> recipePage = recipeService.pageGetRecipeCate(recipetype, pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar ?????? ??????
        model.addAttribute("adminmenu", "?????????");

        return "adminContent";

    }


    //????????? ????????? ?????? ????????? ??????
    @GetMapping("/adminContentAdd")
    public String adminConAdd(){
        return "adminContentAdd";
    }

    //????????? ????????? ?????? ??????
    @PostMapping("/adminContentAddAction")
    public String adminConAddAction(RecipeDto recipeDto){
        recipeService.adminContentAdd(recipeDto);

        return "redirect:/adminContent";

    }
    //????????? ????????? ?????? ????????? ??????
    @GetMapping("/adminContentUpdate/{recipekey}")
    public String adminContentUpdate(@PathVariable("recipekey") Long recipekey, Model model){

        RecipeDto recipeDto = recipeService.getRecipeUp(recipekey);

        model.addAttribute("recipedetail", recipeDto);

        return "adminContentUpdate";

    }

    //????????? ????????? ?????? ??????
    @PostMapping("/recipeUpdate/{recipekey}")
    public String adminRecipeUpdate(@PathVariable("recipekey") Long recipekey, RecipeDto recipeDto){
        recipeService.recipeUpdate(recipekey, recipeDto);

        return "redirect:/adminContent";
    }

    //????????? ????????? ??????
    @GetMapping("/recipesearch")
    public String adminRecipeSearch(@RequestParam(value="keyword") String keyword, @RequestParam(value="select") String select, Model model,
                                    @PageableDefault(size = 5, sort = "recipekey", direction = Sort.Direction.DESC)Pageable pageable){
        Page<Recipe> recipePage = recipeService.pageGetRecipeSearch(keyword,select, pageable);
        model.addAttribute("addContent", recipePage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", recipeService.getConAllListCheck(pageable));
        //adminsidebar ?????? ??????
        model.addAttribute("adminmenu", "?????????");

        return "adminContent";
    }

    //????????? ????????? ??????
    @RequestMapping("/adminContentDelete/{recipekey}")
    public String adminRecipeDelete(@PathVariable("recipekey") Long recipekey){

        recipeService.adminRecipeDelete(recipekey);

        return "redirect:/adminContent";

    }

   
    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetail(@PathVariable("recipekey")Long recipekey, Model model, HttpSession session){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);

        String materialStr = recipeDto.getRecipearrang();

        if(! StringUtils.isEmpty(materialStr)){
            String[] materialList = materialStr.split(",");

            List<Material> materialDtoList = materialService.getMaterialByTitles(materialList);

            model.addAttribute("materialDtoList",materialDtoList);

        }
        model.addAttribute("recipehit", recipeService.creatorupdateView(recipekey));	
        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);
        return "recipedetail";
    }


    // new
    @GetMapping("/new")
    public String newrecipe(Model model){
        List<RecipeDto> recipeDtoList = recipeService.getnewrecipe();
        model.addAttribute("newlist",recipeDtoList);
        return "new";
    }

    // menu
    @GetMapping("/menu/{recipetype}")
    public String menurecipe(@PathVariable("recipetype")String recipetype, Model model){
        List<RecipeDto> recipeDtoList = recipeService.getmenurecipe(recipetype);
        model.addAttribute("menulist",recipeDtoList);
        return "menu";
    }

    /*
    @GetMapping("/recipe/{recipekey}")
    public String recipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum){
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);

        model.addAttribute("recipelist", recipeDtoList);

        return "recipe";
    }

     */



/*

    // ?????????(creatorBest)
    @GetMapping("/recipe/{recipecreator}")
    public String recipeCreator(@PathVariable("recipecreator") String recipecreator, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe(recipecreator);
        List<RecipeDto> recipeDtoList1 = recipeService.getnewrecipe(recipecreator);

        model.addAttribute("recipelist", recipeDtoList);
        model.addAttribute("recipelist", recipeDtoList1);

        return "recipe";
    }


    // ?????????(best)
    @GetMapping("/best/{recipecreator}")
    public String recipebestCreator(@PathVariable("recipecreator") String recipecreator, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe(recipecreator);

        model.addAttribute("recipelist", recipeDtoList);

        return "best";
    }

    // ??????(new)
    @GetMapping("/new/{recipecreator}")
    public String newCreator(@PathVariable("recipecreator") String recipecreator, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getnewrecipe(recipecreator);

        model.addAttribute("recipelist", recipeDtoList);

        return "new";
    }

     */








    /*
    @GetMapping("/recipe")
    public String recipe(Model model){

        List<RecipeDto> recipeDtoList = recipeService.getAllRecipe();

        model.addAttribute("recipeList", recipeDtoList);

        return "recipe";
    }

     */


    /*

    //????????? ?????? ????????? ?????????
    @GetMapping("/recipe/{recipeType}")
    public String materialMainCate(@PathVariable("recipeType") String recipeType, @PathVariable("id") int id, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getRecipe(recipeType);
        model.addAttribute("recipeList", recipeDtoList);

        return "recipe";
    }

     */




}

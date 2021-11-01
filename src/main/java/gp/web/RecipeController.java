package gp.web;



import gp.domain.Material;
import gp.domain.Recipe;
import gp.domain.Review;
import gp.service.MaterialService;
import gp.service.RecipeService;
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
    public String indexrecipe(Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum, @PageableDefault(size = 4, sort = "materialKey", direction = Sort.Direction.DESC)Pageable pageable){
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);
        Page<Material> materialDtoList = materialService.pageGetAllMaterial(pageable);
        model.addAttribute("addMater", materialDtoList);

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
    public String adminConAdd(Model model){

        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "레시피");
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
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "레시피");

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

    /*

    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetail(@PathVariable("recipekey")Long recipekey, Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum, HttpSession session){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);
        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);


        String materialStr = recipeDto.getRecipearrang();

        if(! StringUtils.isEmpty(materialStr)){
            String[] materialList = materialStr.split(",");

            List<Material> materialDtoList = materialService.getMaterialByTitles(materialList);

            model.addAttribute("materialDtoList",materialDtoList);

        }
        model.addAttribute("recipelist", recipeDtoList);
        model.addAttribute("recipehit", recipeService.creatorupdateView(recipekey));
        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);

        return "recipedetail";
    }

     */

    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetail(@PathVariable("recipekey")Long recipekey, Model model,@RequestParam(value = "page",defaultValue = "1") Integer pageNum, HttpSession session){

        MemberDto loginMember=(MemberDto)session.getAttribute("user");
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);


        List<RecipeDto> recipeDtoList = recipeService.getrecipelist(pageNum);
        List<RecipeDto> recipeDtoList1 = recipeService.getrecipepage(pageNum);
        Integer[] pageList= recipeService.getPageList(pageNum);

        model.addAttribute("recipepage",recipeDtoList1);
        model.addAttribute("pagelist",pageList);


        String materialStr = recipeDto.getRecipearrang();

        if(! StringUtils.isEmpty(materialStr)){
            String[] materialList = materialStr.split(",");

            List<Material> materialDtoList = materialService.getMaterialByTitles(materialList);

            model.addAttribute("materialDtoList",materialDtoList);

        }
        model.addAttribute("recipelist", recipeDtoList);
        model.addAttribute("recipehit", recipeService.creatorupdateView(recipekey));
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

    // 조회수(creatorBest)
    @GetMapping("/recipe/{recipecreator}")
    public String recipeCreator(@PathVariable("recipecreator") String recipecreator, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe(recipecreator);
        List<RecipeDto> recipeDtoList1 = recipeService.getnewrecipe(recipecreator);

        model.addAttribute("recipelist", recipeDtoList);
        model.addAttribute("recipelist", recipeDtoList1);

        return "recipe";
    }


    // 조회수(best)
    @GetMapping("/best/{recipecreator}")
    public String recipebestCreator(@PathVariable("recipecreator") String recipecreator, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getbestrecipe(recipecreator);

        model.addAttribute("recipelist", recipeDtoList);

        return "best";
    }

    // 날짜(new)
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

    //레시피 목록 종류별 띄우기
    @GetMapping("/recipe/{recipeType}")
    public String materialMainCate(@PathVariable("recipeType") String recipeType, @PathVariable("id") int id, Model model) {
        List<RecipeDto> recipeDtoList = recipeService.getRecipe(recipeType);
        model.addAttribute("recipeList", recipeDtoList);

        return "recipe";
    }

     */




}

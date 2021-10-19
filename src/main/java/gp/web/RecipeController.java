package gp.web;

import gp.domain.Material;
import gp.service.MaterialService;
import gp.service.RecipeService;
import gp.web.dto.MemberDto;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final MaterialService materialService;

    // main 추천 영상
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

    // 영상 클릭시 조회수 증가
    /*
    @GetMapping("/recipedetail/{recipekey}")
    public String recipedetailrecipe(@PathVariable("recipekey")Long recipekey, Model model){
        RecipeDto recipeDto = recipeService.getRecipe(recipekey);


        model.addAttribute("recipehit", recipeService.creatorupdateView(recipekey));
        model.addAttribute("recipehit",recipeService.updateView(recipekey));
        model.addAttribute("recipeDto",recipeDto);
        return "recipedetail.html";
    }


     */
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

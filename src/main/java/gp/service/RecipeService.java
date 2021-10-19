package gp.service;

import gp.domain.Recipe;
import gp.domain.RecipeRepository;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    private RecipeDto recipeDto;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

    public Long saveRecipe(RecipeDto recipeDto){

        return recipeRepository.save(recipeDto.toEntity()).getRecipekey();
    }
    @Transactional
    public int updateView(Long recipekey) {
        return recipeRepository.updateView(recipekey);

    }

    // creator best 조회수 증가
    @Transactional
    public int creatorupdateView(Long recipekey) {
        return recipeRepository.creatorupdateView(recipekey);

    }

    @Transactional
    public RecipeDto getRecipe(Long recipekey){
    public List<RecipeDto> getAllRecipe(){
        List<Recipe> recipies = recipeRepository.findAll();
        List<RecipeDto> recipeDtoList= new ArrayList<>();

        for (Recipe recipe : recipies){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipetitle(recipe.getRecipetitle())
                    .recipecreator(recipe.getRecipecreator())
                    .recipedetail(recipe.getRecipedetail())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .recipemateriallist(recipe.getRecipemateriallist())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }
    @Transactional
    public RecipeDto getRecipe(Long recipekey){
        Optional<Recipe> recipeWrapper = recipeRepository.findById(recipekey);
        Recipe recipe = recipeWrapper.get();

        RecipeDto recipeDto = RecipeDto.builder()
                .recipekey(recipe.getRecipekey())
                .recipetitle(recipe.getRecipetitle())
                .recipecreator(recipe.getRecipecreator())
                .recipedetail(recipe.getRecipedetail())
                .recipehit(recipe.getRecipehit())
                .recipelink(recipe.getRecipelink())
                .recipetype(recipe.getRecipetype())
                .recipeupdated(recipe.getRecipeupdated())
                .recipemateriallist(recipe.getRecipemateriallist())
                .build();

        return recipeDto;
    }

    private RecipeDto convertEntityToDto(Recipe recipe){
        return RecipeDto.builder()
                .recipekey(recipe.getRecipekey())
                .recipetitle(recipe.getRecipetitle())
                .recipecreator(recipe.getRecipecreator())
                .recipedetail(recipe.getRecipedetail())
                .recipehit(recipe.getRecipehit())
                .recipelink(recipe.getRecipelink())
                .recipetype(recipe.getRecipetype())
                .recipemateriallist(recipe.getRecipemateriallist())
                .recipeupdated(recipe.getRecipeupdated()).build();


    }

    public List<RecipeDto> getrecipelist(Integer pageNum){
        Page<Recipe> page = recipeRepository.findAll(PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC,"recipekey")));

        List<Recipe> recipes = page.getContent();
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for(Recipe recipe : recipes){
            recipeDtoList.add(this.convertEntityToDto(recipe));
        }
        return recipeDtoList;
    }


    // creator best
    public List<RecipeDto> getcreatorbestRecipe(String recipecreator) {
        List<Recipe> recipes = recipeRepository.creatorbestrecipe(recipecreator);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipes){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipedetail(recipe.getRecipedetail())
                    .recipecreator(recipe.getRecipecreator())
                    .recipetitle(recipe.getRecipetitle())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    // creator new
    public List<RecipeDto> getcreatornewRecipe(String recipecreator) {
        List<Recipe> recipes = recipeRepository.creatornewrecipe(recipecreator);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipes){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipedetail(recipe.getRecipedetail())
                    .recipecreator(recipe.getRecipecreator())
                    .recipetitle(recipe.getRecipetitle())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }


    // best

    public List<RecipeDto> getbestrecipe(){
        List<Recipe> recipes = recipeRepository.bestrecipe();
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipes){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipedetail(recipe.getRecipedetail())
                    .recipecreator(recipe.getRecipecreator())
                    .recipetitle(recipe.getRecipetitle())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .recipemateriallist(recipe.getRecipemateriallist())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    // new
    public List<RecipeDto> getnewrecipe(){
        List<Recipe> recipes = recipeRepository.newrecipe();
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipes){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipedetail(recipe.getRecipedetail())
                    .recipecreator(recipe.getRecipecreator())
                    .recipetitle(recipe.getRecipetitle())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    public List<RecipeDto> getmenurecipe(String recipetype){
        List<Recipe> recipes = recipeRepository.menurecipe(recipetype);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipes){
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipe.getRecipekey())
                    .recipehit(recipe.getRecipehit())
                    .recipelink(recipe.getRecipelink())
                    .recipedetail(recipe.getRecipedetail())
                    .recipecreator(recipe.getRecipecreator())
                    .recipetitle(recipe.getRecipetitle())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }



   /*



    //조회수(best)
    public List<RecipeDto> getbestrecipe(String recipecreator) {
        List<Recipe> recipeList = recipeRepository.creatorbestRecipe(recipecreator);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipeEntity : recipeList) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipeEntity.getRecipekey())
                    .recipelink(recipeEntity.getRecipelink())
                    .recipetitle(recipeEntity.getRecipetitle())
                    .recipehit(recipeEntity.getRecipehit()).build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }


    // 날짜(new)
    public List<RecipeDto> getnewrecipe(String recipecreator) {
        List<Recipe> recipeList = recipeRepository.newRecipe(recipecreator);
        List<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipeEntity : recipeList) {
            RecipeDto recipeDto = RecipeDto.builder()
                    .recipekey(recipeEntity.getRecipekey())
                    .recipelink(recipeEntity.getRecipelink())
                    .recipetitle(recipeEntity.getRecipetitle())
                    .recipeupdated(recipeEntity.getRecipeupdated()).build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    */


    public void recipemateriallist(){
        String rm = "h,e,l,l,o";
        String[] arr = rm.split(",");
        for(int i=0; i<arr.length-1; i++);{
            System.out.println(arr[1]);
        }

    }


}

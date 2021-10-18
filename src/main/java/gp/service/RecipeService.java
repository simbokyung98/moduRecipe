package gp.service;

import gp.domain.Material;
import gp.domain.Recipe;
import gp.domain.RecipeRepository;
import gp.web.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

    public Long saveRecipe(RecipeDto recipeDto){
        return recipeRepository.save(recipeDto.toEntity()).getRecipekey();
    }
    @Transactional
    public int updateView(Long recipekey) {
        return recipeRepository.updateView(recipekey);

    }

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
                    .recipearrang(recipe.getRecipearrang())
                    .recipetype(recipe.getRecipetype())
                    .recipeupdated(recipe.getRecipeupdated())
                    .build();
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    //관리자 컨텐츠 전체 조회
    @Transactional
    public Page<Recipe> pageGetAllContent(Pageable pageable){
        return recipeRepository.findAll(pageable);
    }

    //관리자 컨텐츠 카테고리 조회
    @Transactional
    public Page<Recipe> pageGetRecipeCate(String recipetype, Pageable pageable){
        Page<Recipe> recipePage = recipeRepository.findByRecipetype(recipetype, pageable);

        return recipePage;
    }

    //관리자 컨텐츠 페이지 다음장 유무 확인
    @Transactional
    public Boolean getConAllListCheck(Pageable pageable) {
        Page<Recipe> saved = pageGetAllContent(pageable);
        Boolean check = saved.hasNext();

        return check;
    }
    //관리자 컨텐츠 추가
    public void adminContentAdd(RecipeDto recipeDto){

        recipeRepository.save(recipeDto.toEntity());
    }

    //레시피 세부정보 추출
    public RecipeDto getRecipeUp(Long recipekey){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipekey);
        Recipe recipeEntity = optionalRecipe.get();

        RecipeDto recipeDto = RecipeDto.builder()
                .recipekey(recipeEntity.getRecipekey())
                .recipetitle(recipeEntity.getRecipetitle())
                .recipetype(recipeEntity.getRecipetype())
                .recipecreator(recipeEntity.getRecipecreator())
                .recipedetail(recipeEntity.getRecipedetail())
                .recipelink(recipeEntity.getRecipelink())
                .recipehit(recipeEntity.getRecipehit())
                .recipeupdated(recipeEntity.getRecipeupdated())
                .recipearrang(recipeEntity.getRecipearrang())
                .build();

        return recipeDto;

    }

    //레시피 업데이트
    @Transactional
    public void recipeUpdate(Long recipekey, RecipeDto recipeDto){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipekey);
        Recipe recipe = optionalRecipe.get();

        recipe.recipeUpdate(recipeDto.getRecipetitle(), recipeDto.getRecipetype(),recipeDto.getRecipecreator(),
                                recipeDto.getRecipedetail(), recipeDto.getRecipearrang());
    }

    //관리자 레시피 검색
    public Page<Recipe> pageGetRecipeSearch(String keyword, String select, Pageable pageable) {

        if(select.equals("recipetitle")){
            return recipeRepository.findByRecipetitleContaining(keyword, pageable);
        }else if(select.equals("recipecreator")){

            return recipeRepository.findByRecipecreatorContaining(keyword, pageable);
        }
        return null;
    }

    //관리자 레시피 삭제
    @Transactional
    public void adminRecipeDelete(Long recipekey){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipekey);
        Recipe recipe = optionalRecipe.get();

        recipeRepository.delete(recipe);
    }

}

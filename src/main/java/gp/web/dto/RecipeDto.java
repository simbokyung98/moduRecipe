package gp.web.dto;

import gp.domain.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {

    private Long recipekey;
    private String recipetitle;
    private String recipetype;
    private String recipecreator;
    private String recipedetail;
    private String recipelink;
    private Integer recipehit;
    private Date recipeupdated;
    //private Material material;

    @Builder
    public RecipeDto(Long recipekey, String recipetitle, String recipetype, String recipecreator, String recipedetail, String recipelink, int recipehit, Date recipeupdated) {
        this.recipekey = recipekey;
        this.recipetitle = recipetitle;
        this.recipetype = recipetype;
        this.recipecreator = recipecreator;
        this.recipedetail = recipedetail;
        this.recipelink = recipelink;
        this.recipehit = recipehit;
        this.recipeupdated = recipeupdated;

    }

    public Recipe toEntity(){
        return Recipe.builder()
                .recipekey(recipekey)
                .recipetitle(recipetitle)
                .recipetype(recipetype)
                .recipecreator(recipecreator)
                .recipedetail(recipedetail)
                .recipelink(recipelink)
                .recipehit(recipehit)
                .recipeupdated(recipeupdated)
                .build();
    }

    /*
    public void updateView(RecipeDto recipeDto) throws Exception {
        // 조회수 업데이트
        Session.("Recipe.updateView", recipeDto);
    }

     */
}

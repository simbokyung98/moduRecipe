package gp.web.dto;

import gp.domain.Material;
import gp.domain.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import java.util.List;

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
    private String recipearrang;
    private Date recipeupdated;






    @Builder
    public RecipeDto(Long recipekey, String recipetitle, String recipetype, String recipecreator, String recipedetail, String recipelink, int recipehit, Date recipeupdated,String recipearrang) {
        this.recipekey = recipekey;
        this.recipetitle = recipetitle;
        this.recipetype = recipetype;
        this.recipecreator = recipecreator;
        this.recipedetail = recipedetail;
        this.recipelink = recipelink;
        this.recipehit = recipehit;
        this.recipeupdated = recipeupdated;
        this.recipearrang = recipearrang;




    }

    public Recipe toEntity(){
        return Recipe.builder()
                .recipekey(recipekey)
                .recipetitle(recipetitle)
                .recipetype(recipetype)
                .recipecreator(recipecreator)
                .recipedetail(recipedetail)
                .recipelink(recipelink)
                .recipeupdated(recipeupdated)
                .recipearrang(recipearrang)
                //.recipehit(recipehit)
                .build();

    }

}

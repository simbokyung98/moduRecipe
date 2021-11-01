package gp.web.dto;

import gp.domain.Material;
import gp.domain.Recipe;
import gp.domain.RecipeMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeMaterialDto {

    private Long recipematerialkey;
    private Recipe recipe;
    private Material material1;
    private Material material2;
    private Material material3;
    private Material material4;
    private Material material5;

    @Builder
    public RecipeMaterialDto(Long recipematerialkey, Recipe recipe, Material material1, Material material2, Material material3, Material material4, Material material5){
        this.recipematerialkey=recipematerialkey;
        this.recipe=recipe;
        this.material1=material1;
        this.material2=material2;
        this.material3=material3;
        this.material4=material4;
        this.material5=material5;
    }

    public RecipeMaterial toEntity(){
        return RecipeMaterial.builder()
                .recipematerialkey(recipematerialkey)
                .recipe(recipe)
                .material1(material1)
                .material2(material2)
                .material3(material3)
                .material4(material4)
                .material5(material5).build();
    }
}



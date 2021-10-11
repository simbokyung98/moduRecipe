package gp.service;

import gp.domain.MaterialRepository;
import gp.domain.Recipe;
import gp.domain.RecipeMaterial;
import gp.domain.RecipeRepository;
import gp.web.dto.RecipeMaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeMaterialService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;

    /*public RecipeMaterial toEntity(RecipeMaterialDto recipeMaterialDto){
        Recipe recipe = recipeRepository.findById(recipeMaterialDto.getRecipe()).get();
    }*/
}

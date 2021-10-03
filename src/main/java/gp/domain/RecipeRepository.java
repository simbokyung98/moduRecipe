package gp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {


    @Query(value = "select * from recipe where recipeHit=(select max(recipehit)from recipe)",nativeQuery = true)
    List<Recipe> bestrecipe();

    @Modifying
    @Query("update Recipe p set p.recipehit = p.recipehit + 1 where p.recipekey = :recipekey")
    int updateView(Long recipekey);
}

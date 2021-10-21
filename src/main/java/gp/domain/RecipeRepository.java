package gp.domain;

import gp.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public Page<Recipe> findByRecipetype(String recipetype, Pageable pageable);
    public Page<Recipe> findByRecipetitleContaining(String recipetitle, Pageable pageable);
    public Page<Recipe> findByRecipecreatorContaining(String recipecreator, Pageable pageable);

    // best조회수
    @Query(value = "select * from recipe order by recipehit desc",nativeQuery = true)
    List<Recipe> bestrecipe();


    // best조회수 증가
    @Modifying
    @Query("update Recipe p set p.recipehit = p.recipehit + 1 where p.recipekey = :recipekey")
    int updateView(Long recipekey);

    // new
    @Query(value = "select * from recipe order by recipeupdated desc",nativeQuery = true)
    List<Recipe> newrecipe();

    //---------------------------------------------------------------------------------------------

    // creator 조회수
    @Query(value = "select * from recipe where recipecreator = :recipecreator order by recipehit desc",nativeQuery = true)
    List<Recipe> creatorbestrecipe(@Param("recipecreator") String recipecreator);

    // creator 조회수 증가
    @Modifying
    @Query("update Recipe p set p.recipehit = p.recipehit + 1 where p.recipekey = :recipekey")
    int creatorupdateView(Long recipekey);

    // creator new
    @Query(value = "select * from recipe where recipecreator = :recipecreator order by recipeupdated desc",nativeQuery = true)
    List<Recipe> creatornewrecipe(@Param("recipecreator") String recipecreator);


    //---------------------------------------------------------------------------------------------

    // menu
    @Query(value = "select * from recipe where recipetype = :recipetype order by recipehit desc",nativeQuery = true)
    List<Recipe> menurecipe(@Param("recipetype") String recipetype);







    /*
    @Query(value = "select * from recipe where recipeHit=(select max(recipehit)from recipe)",nativeQuery = true)
    List<Recipe> bestrecipe();

     */



    /*
    // 조회수 증가
    @Modifying
    @Query("update Recipe p set p.recipehit = p.recipehit + 1 where p.recipekey = :recipekey")
    int updateView(Long recipekey);

    public Page<Recipe> findByRecipetype(String recipetype, Pageable pageable);
    public Page<Recipe> findByRecipetitleContaining(String recipetitle, Pageable pageable);
    public Page<Recipe> findByRecipecreatorContaining(String recipecreator, Pageable pageable);
     */


    /*
    // 조회수 불러오기(Best)
    @Modifying
    @Query(value = "select * from Recipe where recipecreator = :recipecreator order by recipehit desc", nativeQuery = true)
    public List<Recipe> creatorbestRecipe(@Param("recipecreator") String recipecreator);




    // 날짜 불러오기(New)
    @Query(value = "select * from Recipe where recipecreator = :recipecreator order by recipeupdated desc", nativeQuery = true)
    public List<Recipe> newRecipe(@Param("recipecreator") String recipecreator);



     */



    public List<Recipe> findByRecipecreator(String recipecreator);

}

package gp.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipekey;

    @Column(name = "recipetitle")
    private String recipetitle;

    @Column(name = "recipetype")
    private String recipetype;

    @Column(name = "recipecreator")
    private String recipecreator;

    @Column(name = "recipedetail")
    private String recipedetail;

    @Column(name = "recipelink")
    private String recipelink;

    @Column(name = "recipehit")
    private Integer recipehit;

    @Column(name = "recipeupdated")
    private Date recipeupdated;

    @ManyToOne
    @JoinColumn(name = "materialKey")
    private Material material;

    @Builder
    public Recipe(Long recipekey, String recipetitle, String recipetype, String recipecreator, String recipedetail, String recipelink, int recipehit, Date recipeupdated, Material material){
        this.recipekey=recipekey;
        this.recipetitle=recipetitle;
        this.recipetype=recipetype;
        this.recipecreator=recipecreator;
        this.recipedetail=recipedetail;
        this.recipelink=recipelink;
        this.recipehit=recipehit;
        this.recipeupdated=recipeupdated;
        this.material=material;
    }

}

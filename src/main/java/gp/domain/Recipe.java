package gp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "recipekey")
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

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "recipeupdated")
    private Date recipeupdated;

    @JsonIgnoreProperties({"recipe"})
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<Review> reviewlist;



    @Builder
    public Recipe(Long recipekey, String recipetitle, String recipetype, String recipecreator, String recipedetail, String recipelink, int recipehit, Date recipeupdated){
        this.recipekey=recipekey;
        this.recipetitle=recipetitle;
        this.recipetype=recipetype;
        this.recipecreator=recipecreator;
        this.recipedetail=recipedetail;
        this.recipelink=recipelink;
        this.recipehit=recipehit;
        this.recipeupdated=recipeupdated;

    }

}

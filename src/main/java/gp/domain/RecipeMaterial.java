package gp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "recipematerial")
public class RecipeMaterial {

    @Id
    @Column(name = "recipematerialkey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipematerialkey;


    @OneToOne
    @JoinColumn(name = "recipekey")
    private Recipe recipe;

    @OneToMany
    @JoinColumn(name = "materialkey1")
    private Collection<Material> material1;

    @OneToMany
    @JoinColumn(name = "materialkey2")
    private Collection<Material> material2;

    @OneToMany
    @JoinColumn(name = "materialkey3")
    private Collection<Material> material3;

    @OneToMany
    @JoinColumn(name = "materialkey4")
    private Collection<Material> material4;

    @OneToMany
    @JoinColumn(name = "materialkey5")
    private Collection<Material> material5;

    @Builder
    public RecipeMaterial(Long recipematerialkey, Recipe recipe, Material material1, Material material2, Material material3, Material material4, Material material5){
        this.recipematerialkey=recipematerialkey;

    }

}

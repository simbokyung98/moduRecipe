package gp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "reviewkey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewkey;

    @Column(name = "reviewcontent")
    private String reviewcontent;

    @Column(name = "reviewupdated")
    private Date reviewupdated;

    @ManyToOne
    @JoinColumn(name = "recipekey")
    private Recipe recipe;

    @Column(name = "reviewwriter")
    private String reviewwriter;

    @Builder
    public Review(Long reviewkey, String reviewcontent, Date reviewupdated, Recipe recipe, String reviewwriter){
        this.reviewkey=reviewkey;
        this.reviewcontent=reviewcontent;
        this.reviewupdated=reviewupdated;
        this.recipe=recipe;
        this.reviewwriter=reviewwriter;
    }

}

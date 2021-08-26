package gp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "oftenquestion")
public class OftenQuestionEntity {

    @Id
    @Column(name="oqKey", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oq_key;

    @Column(name="oqTitle", nullable = false)
    private String oq_title;

    @Column(name="oqContent", nullable = false)
    private String oq_content;

    @Builder
    public OftenQuestionEntity(int oq_key, String oq_title, String oq_content){
        this.oq_key=oq_key;
        this.oq_title=oq_title;
        this.oq_content=oq_content;
    }
}

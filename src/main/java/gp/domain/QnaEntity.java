package gp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "qna")
public class QnaEntity {

    @Id
    @Column(name="qna_Key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnakey;

    @Column(name="qna_Title", nullable = true)
    private String qnatitle;

    @Column(name = "qna_Content" , nullable = true)
    private String qnacontent;

    @Column(name = "answer_Content", nullable = true)
    private String answercontent;

    @Column(name ="qna_Date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qnaDate;

    @Column(name = "qna_Writer")
    private String qnawriter;


    @Column(name = "answer_State",nullable = false, columnDefinition = "답변대기")

    private String answerstate;

    @Builder
    public QnaEntity(Long qnakey, String qnatitle, String qnacontent, String answercontent, Date qnadate, String answerstate,String qnawriter){
        this.qnakey=qnakey;
        this.qnatitle=qnatitle;
        this.qnacontent=qnacontent;
        this.answercontent=answercontent;
        this.qnaDate=qnadate;
        this.answerstate=answerstate;
        this.qnawriter=qnawriter;

    }

}

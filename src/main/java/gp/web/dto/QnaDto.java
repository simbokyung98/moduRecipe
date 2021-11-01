package gp.web.dto;

import gp.domain.QnaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaDto {
    private Long qnakey;
    private String qnatitle;
    private String qnacontent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qnadate;
    private String answerstate;
    private String answercontent;
    private String qnawriter;

    public QnaEntity toEntity(){
        QnaEntity qnaEntity = QnaEntity.builder()
                .qnakey(qnakey)
                .qnatitle(qnatitle)
                .qnacontent(qnacontent)
                .qnadate(qnadate)
                .answerstate(answerstate)
                .answercontent(answercontent)
                .qnawriter(qnawriter)
                .build();
        return qnaEntity;
    }

    @Builder
    public QnaDto(Long qnakey, String qnatitle, String qnacontent, Date qnadate, String answerstate, String answercontent, String qnawriter){

        this.qnakey=qnakey;
        this.qnatitle=qnatitle;
        this.qnacontent=qnacontent;
        this.qnadate=qnadate;
        this.answerstate=answerstate;
        this.answercontent=answercontent;
        this.qnawriter=qnawriter;
    }


}

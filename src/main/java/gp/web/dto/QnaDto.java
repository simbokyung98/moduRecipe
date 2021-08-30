package gp.web.dto;

import gp.domain.QnaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaDto {
    private Long qnakey;
    private String qnatitle;
    private String qnacontent;
    private Date qnadate;
    private Boolean answerstate;
    private String answercontent;

    public QnaEntity toEntity(){
        QnaEntity qnaEntity = QnaEntity.builder()
                .qnakey(qnakey)
                .qnatitle(qnatitle)
                .qnacontent(qnacontent)
                .qnadate(qnadate)
                .answerstate(answerstate)
                .answercontent(answercontent)
                .build();
        return qnaEntity;
    }

    @Builder
    public QnaDto(Long qnakey, String qnatitle, String qnacontent, Date qnadate, Boolean answerstate, String answercontent){

        this.qnakey=qnakey;
        this.qnatitle=qnatitle;
        this.qnacontent=qnacontent;
        this.qnadate=qnadate;
        this.answerstate=answerstate;
        this.answercontent=answercontent;

    }


}

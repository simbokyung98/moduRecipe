package gp.web.dto;


import gp.domain.QnaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QnaDto {

    private int qakey;
    private String qatitle;
    private String qatype;
    private String qacontent;
    private LocalDateTime qadate;
    private int qacount;

    public QnaEntity toEntity(){
        QnaEntity qnaEntity = QnaEntity.builder()
                .qakey(qakey)
                .qatitle(qatitle)
                .qatype(qatype)
                .qacount(qacount)
                .qadate(qadate)
                .build();

        return qnaEntity;
    }

    @Builder
    public QnaDto(int qakey, String qatitle, String qatype, LocalDateTime qadate, int qacount){
        this.qakey=qakey;
        this.qatitle=qatitle;
        this.qatype=qatype;
        this.qadate=qadate;
        this.qacount=qacount;
    }
}




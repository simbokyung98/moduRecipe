package gp.web.dto;

import gp.domain.NoticeEntity;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeDto {

    private Long noticekey;
    private String noticetitle;
    private String noticecontent;

    public NoticeEntity toEntity(){
        NoticeEntity noticeEntity = NoticeEntity.builder()
                .noticekey(noticekey)
                .title(noticetitle)
                .content(noticecontent)
                .build();

        return noticeEntity;
    }

    @Builder
    public NoticeDto(Long noticekey, String title,String content){
        this.noticekey=noticekey;
        this.noticetitle=title;
        this.noticecontent=content;



    }
}

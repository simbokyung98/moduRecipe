package gp.web.dto;

import gp.domain.OftenQuestionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OftenQuestionDto {
    private int oq_key;
    private String oq_title;
    private String oq_content;

    public OftenQuestionEntity OqtoEntity(){
        OftenQuestionEntity oftenQuestionEntity = OftenQuestionEntity.builder()
                .oq_key(oq_key)
                .oq_title(oq_title)
                .oq_content(oq_content)
                .build();

        return oftenQuestionEntity;
    }

    @Builder
    public OftenQuestionDto(int oq_Key, String oq_Title, String oq_Content){
        this.oq_key=oq_Key;
        this.oq_title=oq_Title;
        this.oq_content=oq_Content;
    }

}

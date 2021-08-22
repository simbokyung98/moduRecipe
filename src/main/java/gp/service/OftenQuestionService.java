package gp.service;

import gp.domain.OftenQuestionEntity;
import gp.domain.OftenQuestionRepository;
import gp.web.dto.OftenQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OftenQuestionService {
    private final OftenQuestionRepository oftenQuestionRepository;

    public int saveOq(OftenQuestionDto oftenQuestionDto){return oftenQuestionRepository.save(oftenQuestionDto.OqtoEntity()).getOq_key();}

    public List<OftenQuestionDto> getAllOftenQuestion(){
        List<OftenQuestionEntity> oftenQuestionEntities = oftenQuestionRepository.findAll();
        List<OftenQuestionDto> oftenQuestionDtoList = new ArrayList<>();

        for(OftenQuestionEntity oftenQuestionEntity:oftenQuestionEntities){
            OftenQuestionDto oftenQuestionDto = OftenQuestionDto.builder()
                    .oq_Key(oftenQuestionEntity.getOq_key())
                    .oq_Title(oftenQuestionEntity.getOq_title())
                    .oq_Content(oftenQuestionEntity.getOq_content()).build();
            oftenQuestionDtoList.add(oftenQuestionDto);

        }
        return oftenQuestionDtoList;
    }
}

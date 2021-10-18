package gp.service;

import gp.domain.QnaEntity;
import gp.domain.QnaRepository;
import gp.web.dto.QnaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaService {

    @Autowired private QnaRepository qnaRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수


    public Long saveQna(QnaDto qnaDto){


        qnaRepository.updateAnswerState();
        return qnaRepository.save(qnaDto.toEntity()).getQnakey();}

    public List<QnaDto> getAllQna() {

        List<QnaEntity> qnaEntities = qnaRepository.findAll();
        List<QnaDto> qnaDtoList = new ArrayList<>();




        for (QnaEntity qnaEntity : qnaEntities) {
            QnaDto qnaDto = QnaDto.builder()
                    .qnakey(qnaEntity.getQnakey())
                    .qnatitle(qnaEntity.getQnatitle())
                    .qnacontent(qnaEntity.getQnacontent())
                    .qnadate(qnaEntity.getQnaDate())
                    .answercontent(qnaEntity.getAnswercontent())
                    .qnawriter(qnaEntity.getQnawriter())
                    .answerstate(qnaEntity.getAnswerstate()).build();
            qnaDtoList.add(qnaDto);
        }

        return qnaDtoList;

    }
    @Transactional
    public QnaDto getQna(Long qnakey){
        Optional<QnaEntity> qnaEntityWrapper = qnaRepository.findById(qnakey);
        QnaEntity qnaEntity = qnaEntityWrapper.get();

        QnaDto qnaDto = QnaDto.builder()
                .qnakey(qnaEntity.getQnakey())
                .qnatitle(qnaEntity.getQnatitle())
                .qnacontent(qnaEntity.getQnacontent())
                .qnadate(qnaEntity.getQnaDate())
                .answerstate(qnaEntity.getAnswerstate())
                .answercontent(qnaEntity.getAnswercontent())
                .qnawriter(qnaEntity.getQnawriter())
                .build();

        return qnaDto;
    }
    public List<QnaDto> getMyQna(String qnawriter){
        List<QnaEntity> qnaList = qnaRepository.findByqnawriter(qnawriter);
        List<QnaDto> qnaDtoList = new ArrayList<>();

        for(QnaEntity qnaEntity : qnaList){
            QnaDto qnaDto = QnaDto.builder()
                    .qnakey(qnaEntity.getQnakey())
                    .qnatitle(qnaEntity.getQnatitle())
                    .qnacontent(qnaEntity.getQnacontent())
                    .qnadate(qnaEntity.getQnaDate())
                    .answerstate(qnaEntity.getAnswerstate())
                    .answercontent(qnaEntity.getAnswercontent())
                    .qnawriter(qnaEntity.getQnawriter())
                    .build();
            qnaDtoList.add(qnaDto);
        }
        return qnaDtoList;
    }
    private  QnaDto convertEntityToDto(QnaEntity qnaEntity){
        return QnaDto.builder()
                .qnakey(qnaEntity.getQnakey())
                .qnatitle(qnaEntity.getQnatitle())
                .qnacontent(qnaEntity.getQnacontent())
                .qnadate(qnaEntity.getQnaDate())
                .answercontent(qnaEntity.getAnswercontent())
                .answerstate(qnaEntity.getAnswerstate())
                .build();

    }

    public List<QnaDto> getqnalist(Integer pageNum){
        Page<QnaEntity> page= qnaRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC,"qnakey")));

        List<QnaEntity> qnaEntities = page.getContent();
        List<QnaDto> qnaDtoList = new ArrayList<>();

        for (QnaEntity qnaEntity : qnaEntities){
            qnaDtoList.add(this.convertEntityToDto(qnaEntity));
        }

        return qnaDtoList;
    }
    public  List<QnaDto>getnullqnalist(){
        List<QnaEntity> qnaEntities = qnaRepository.nullfind();
        List<QnaDto> qnaDtoList = new ArrayList<>();

        for (QnaEntity qnaEntity : qnaEntities){
            QnaDto qnaDto = QnaDto.builder()
                    .qnakey(qnaEntity.getQnakey())
                    .qnacontent(qnaEntity.getQnacontent())
                    .qnatitle(qnaEntity.getQnatitle())
                    .qnadate(qnaEntity.getQnaDate())
                    .qnawriter(qnaEntity.getQnawriter())
                    .build();
            qnaDtoList.add(qnaDto);
        }
        return qnaDtoList;
    }
    public List<QnaDto> getanswerqnalist(){
        List<QnaEntity> qnaEntities = qnaRepository.find();
        List<QnaDto> qnaDtoList = new ArrayList<>();

        for (QnaEntity qnaEntity : qnaEntities){
            QnaDto qnaDto = QnaDto.builder()
                    .qnakey(qnaEntity.getQnakey())
                    .qnacontent(qnaEntity.getQnacontent())
                    .qnatitle(qnaEntity.getQnatitle())
                    .qnadate(qnaEntity.getQnaDate())
                    .qnawriter(qnaEntity.getQnawriter())
                    .build();
            qnaDtoList.add(qnaDto);
        }
        return qnaDtoList;
    }

    @Transactional
    public List<QnaEntity> updateAnswerState(){
        return qnaRepository.updateAnswerState();
    }

    @Transactional
    public void deleteQna(Long qnakey){
        qnaRepository.deleteById(qnakey);
    }

    @Transactional
    public Long getQnaCount(){return qnaRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

// 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getQnaCount());

// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
        Integer blockStartPageNum =
                (curPageNum <= BLOCK_PAGE_NUM_COUNT / 2)
                        ? 1
                        : curPageNum - BLOCK_PAGE_NUM_COUNT / 2;


// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT - 1 )
                ?curPageNum + BLOCK_PAGE_NUM_COUNT - 1
                : totalLastPageNum;

// 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

// 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

}

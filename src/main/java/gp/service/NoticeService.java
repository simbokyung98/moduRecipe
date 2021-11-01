package gp.service;

import gp.domain.NoticeEntity;
import gp.domain.NoticeRepository;
import gp.web.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수




    public Long saveNotice(NoticeDto noticeDto){
        return noticeRepository.save(noticeDto.toEntity()).getNoticekey();
    }



    public List<NoticeDto> getAllNotice(){
        List<NoticeEntity> noticeEntities = noticeRepository.findAll();
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for(NoticeEntity noticeEntity:noticeEntities){
            NoticeDto noticeDto = NoticeDto.builder()
                    .noticekey(noticeEntity.getNoticekey())
                    .title(noticeEntity.getNoticetitle())
                    .content(noticeEntity.getNoticecontent()).build();
            noticeDtoList.add(noticeDto);

        }
        return noticeDtoList;
    }

    @Transactional
    public NoticeDto getNotice(Long noticekey) {
        Optional<NoticeEntity> noticeEntityWrapper = noticeRepository.findById(noticekey);
        NoticeEntity noticeEntity = noticeEntityWrapper.get();

        NoticeDto noticeDto = NoticeDto.builder()
                .noticekey(noticeEntity.getNoticekey())
                .title(noticeEntity.getNoticetitle())
                .content(noticeEntity.getNoticecontent())
                .build();


        return noticeDto;
    }
    private NoticeDto convertEntityToDto(NoticeEntity noticeEntity) {
        return NoticeDto.builder()
                .noticekey(noticeEntity.getNoticekey())
                .title(noticeEntity.getNoticetitle())
                .content(noticeEntity.getNoticetitle())
                .build();
    }
    public List<NoticeDto> getnoticelist(Integer pageNum) {
        Page<NoticeEntity> page = noticeRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "noticekey")));

        List<NoticeEntity> noticeEntities = page.getContent();
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for (NoticeEntity noticeEntity : noticeEntities) {
            noticeDtoList.add(this.convertEntityToDto(noticeEntity));
        }

        return noticeDtoList;
    }
    @Transactional
    public void deletNotice(Long noticekey) {
        noticeRepository.deleteById(noticekey);
    }

    @Transactional
    public Long getnoticeCount() {
        return noticeRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

// 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getnoticeCount());

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

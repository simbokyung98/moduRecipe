package gp.web;

import gp.domain.NoticeEntity;
import gp.domain.NoticeRepository;
import gp.service.NoticeService;
import gp.web.dto.NoticeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class NoticeController {

    private NoticeService noticeService;
    private NoticeRepository noticeRepository;

    @GetMapping("/notionmain")
    public String notionmain(Model model, @RequestParam(value = "page", defaultValue = "1")Integer pageNum)
    {
        List<NoticeDto> noticeDtoList = noticeService.getnoticelist(pageNum);
        Integer[] pageList = noticeService.getPageList(pageNum);

        model.addAttribute("noticelist",noticeDtoList);
        model.addAttribute("pageList",pageList);

        return "notionmain";
    }

    @GetMapping("/notionwrite")
    public  String notionwrite(Model model){
        model.addAttribute("notice", new NoticeEntity());
        return "notionwrite";
    }
    @PostMapping("/notionwrite")
    public String writesubmit(@ModelAttribute NoticeDto noticeDto){
        noticeService.saveNotice(noticeDto);
        return ("redirect:/notionmain");
    }
    @GetMapping("/notiondetail/{noticekey}")
    public String detail(@PathVariable("noticekey") Long noticekey, Model model) {
        NoticeDto noticeDto = noticeService.getNotice(noticekey);

        model.addAttribute("noticeDto", noticeDto);
        return "notionDetail.html";
    }
    @DeleteMapping("/notionmain/{noticekey}")
    public String delete(@PathVariable("noticekey") Long noticekey) {
        noticeService.deletNotice(noticekey);

        return "redirect:/notionmain";
    }



   /* @GetMapping("/notionmain")
    public String notionwrite(Model model, @RequestParam(required = false) Long notion_key){
        if(notion_key==null) {
            model.addAttribute("notice", new NoticeEntity());
        }else {
            NoticeEntity noticeEntity = noticeRepository.findById(notion_key).orElse(null);
            model.addAttribute("notice",noticeEntity);
        }
        return "notionwrite";
    }

    //board = entity


    @PostMapping("/notionwrite")
    public String writenotice(@ModelAttribute NoticeEntity noticeEntity){
        noticeRepository.save(noticeEntity);
        return "redirect:/notionmain";
    }

*/
}

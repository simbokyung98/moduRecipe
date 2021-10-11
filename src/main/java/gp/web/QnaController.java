package gp.web;

import gp.domain.QnaEntity;
import gp.domain.QnaRepository;
import gp.service.QnaService;
import gp.web.dto.MemberDto;
import gp.web.dto.QnaDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor

public class QnaController {


    private final HttpSession session;
    @Autowired
    private QnaService qnaService;


    @GetMapping("/myqnamain")
    public String myqnamain(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,HttpSession httpSession) {


        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }

        List<QnaDto> qnaDtoList = qnaService.getqnalist(pageNum);
        Integer[] pageList = qnaService.getPageList(pageNum);

        model.addAttribute("qnalist", qnaDtoList);
        model.addAttribute("pageList", pageList);


        return "myqnamain";
    }

    @GetMapping("/qnawrite")
    public String qnawrite(Model model) {
        model.addAttribute("qna", new QnaEntity());
        return "qnawrite";
    }

    @PostMapping("/qnawrite")
    public String qnasubmit(@ModelAttribute QnaDto qnaDto, HttpSession session) {
        String qnawriter=(String)session.getAttribute("username");
        qnaDto.setQnawriter(qnawriter);
        qnaService.saveQna(qnaDto);

        return ("redirect:/myqnamain");
    }

    @GetMapping("/adminqna")
    public String adminqna(Model model) {
        List<QnaDto> qnaDtoList = qnaService.getAllQna();
        List<QnaDto> qnaDtoList2 = qnaService.getanswerqnalist();
        List<QnaDto> qnaDtoList3 = qnaService.getnullqnalist();

        model.addAttribute("qnalist", qnaDtoList);
        model.addAttribute("answerlist",qnaDtoList2);
        model.addAttribute("nulllist",qnaDtoList3);
        return "/adminquestion";
    }

    @GetMapping("/adminqnadetail")
    public String adminqnaanswer(Model model) {
        model.addAttribute("qna", new QnaEntity());
        return "adminqnadetail";
    }

    @GetMapping("/adminqnadetail/{qnakey}")
    public String anminqnadetail(@PathVariable("qnakey") Long qnakey, Model model) {
        QnaDto qnaDto = qnaService.getQna(qnakey);
        model.addAttribute("qnaDto", qnaDto);
        return "adminQuestionDetail.html";
    }

    @PutMapping("/adminqnadetail/{qnakey}")
    public String qnaanswer(@ModelAttribute QnaDto qnaDto) {


        qnaService.saveQna(qnaDto);
        return ("redirect:/adminqna");

    }

}


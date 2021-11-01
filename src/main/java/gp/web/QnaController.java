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
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor

public class QnaController {


    @Autowired
    QnaRepository qnaRepository;
    private final HttpSession session;
    @Autowired
    private QnaService qnaService;


    @GetMapping("/myqnamain")
    public String myqnamain(Model model) {

        MemberDto user = (MemberDto)session.getAttribute("user");
        List<QnaDto> qnaDtoList = qnaService.getMyQna(user.getUsername());
        model.addAttribute("qnaList", qnaDtoList);



        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }

        return "/myqnamain";
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
        String answerstate="답변대기";
        qnaDto.setAnswerstate(answerstate);
        qnaService.saveQna(qnaDto);

        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }



        return ("redirect:/myqnamain");
    }

    @GetMapping("/adminqna")
    public String adminqna(Model model) {
        List<QnaDto> qnaDtoList = qnaService.getAllQna();
        List<QnaDto> qnaDtoList2 = qnaService.getanswerqnalist();
        List<QnaDto> qnaDtoList3 = qnaService.getnullqnalist();
        model.addAttribute("adminmenu", "질문");

        model.addAttribute("qnalist", qnaDtoList);
        model.addAttribute("answerlist",qnaDtoList2);
        model.addAttribute("nulllist",qnaDtoList3);
        return "/adminquestion";
    }

    @GetMapping("/adminqnadetail")
    public String adminqnaanswer(Model model) {
        model.addAttribute("adminmenu", "질문");
        model.addAttribute("qna", new QnaEntity());

        return "adminqnadetail";
    }

    @GetMapping("/adminqnadetail/{qnakey}")
    public String anminqnadetail(@PathVariable("qnakey") Long qnakey, Model model) {
        model.addAttribute("adminmenu", "질문");

        QnaDto qnaDto = qnaService.getQna(qnakey);
        model.addAttribute("qnaDto", qnaDto);
        return "adminQuestionDetail.html";
    }


    @PutMapping("/adminqnadetail/{qnakey}")
    public String qnaanswer(@ModelAttribute QnaDto qnaDto) {
        String updatestate = "답변완료";
        String state = qnaDto.getAnswercontent();
        Date date = new Date();
        if(state!=null){
            qnaDto.setAnswerstate(updatestate);
        }
        qnaDto.setQnadate(date);

        qnaService.saveQna(qnaDto);
        return ("redirect:/adminqna");

    }
    @GetMapping("/qnadetail/{qnakey}")
    public String qnadetail(@PathVariable("qnakey") Long qnakey, Model model) {

        QnaDto qnaDto = qnaService.getQna(qnakey);
        model.addAttribute("qnaDto", qnaDto);
        return "questionDetail.html";
    }

}

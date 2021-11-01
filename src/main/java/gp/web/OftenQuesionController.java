package gp.web;

import gp.domain.*;
import gp.service.NoticeService;
import gp.service.OftenQuestionService;
import gp.web.dto.NoticeDto;
import gp.web.dto.OftenQuestionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class OftenQuesionController {

    private OftenQuestionService oftenQuestionService;
    private OftenQuestionRepository oftenQuestionRepository;


    @GetMapping("/question")
    public String question(Model model){

        List<OftenQuestionDto>   oftenQuestionDtoList = oftenQuestionService.getAllOftenQuestion();

        model.addAttribute("questionlist", oftenQuestionDtoList);
        return "/question";
    }

    @GetMapping("/questionwrite")
    public String questionwrite(Model model){
        model.addAttribute("question", new OftenQuestionEntity());
        return "/questionwrite";
    }
    @PostMapping("questionwrite")
    public String questionsubmit(@ModelAttribute OftenQuestionDto oftenQuestionDto){
        oftenQuestionService.saveOq(oftenQuestionDto);
        return ("redirect:/question");
    }




}

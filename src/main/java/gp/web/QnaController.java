package gp.web;

import gp.domain.QnaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class QnaController {

    private QnaRepository qnaRepository;



}

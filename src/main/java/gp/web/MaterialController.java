package gp.web;

import gp.service.MaterialService;
import gp.web.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/meterialMain")
    public String meterrialMain(Model model){

        List<MaterialDto> materialDtoList = materialService.getAllMaterial();

        model.addAttribute("materialList", materialDtoList);

        return "meterialMain";
    }

    //재료 목록 종류별 띄우기
    @GetMapping("/meterialMain/{materialMainCate}")
    public String materialMainCate(@PathVariable("materialMainCate") String materialMainCate, Model model){
        List<MaterialDto> materialDtoList =materialService.getCateMaterial(materialMainCate);

        model.addAttribute("materialList", materialDtoList);

        return "meterialMain";
    }

    //재료 세부 띄우기
    @GetMapping("/meter_detail/{materialKey}")
    public String meterdetail(@PathVariable("materialKey") Long materialKey, Model model){

        MaterialDto materialDto = materialService.getMaterial(materialKey);

        model.addAttribute("material", materialDto);

        return "meter_detail";
    }

    @GetMapping("/adminMater")
    public String adminMater(){
        return "adminMeter";
    }

    @GetMapping("/adminMaterialAdd")
    public String adminMaterialAdd(){
        return "adminMeterAdd";
    }
}


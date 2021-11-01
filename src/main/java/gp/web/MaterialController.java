package gp.web;

import gp.domain.Material;
import gp.service.MaterialService;
import gp.web.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final SimpleDateFormat dateForServer = new SimpleDateFormat("yyyyMMddHHmmss_");

    //재료 전체 띄우기
    @GetMapping("/meterialMain")
    public String meterrialMain(Model model){

        List<MaterialDto> materialDtoList = materialService.getAllMaterial();

        model.addAttribute("materialList", materialDtoList);
        model.addAttribute("materH2", "전체");

        return "meterialMain";
    }

    //재료 목록 종류별 띄우기
    @GetMapping("/meterialMain/{materialMainCate}")
    public String materialMainCate(@PathVariable("materialMainCate") String materialMainCate, Model model){
        List<MaterialDto> materialDtoList =materialService.getCateMaterial(materialMainCate);

        model.addAttribute("materialList", materialDtoList);
        model.addAttribute("materH2", materialMainCate);

        return "meterialMain";
    }

    //재료 세부 띄우기
    @GetMapping("/meter_detail/{materialKey}")
    public String meterdetail(@PathVariable("materialKey") Long materialKey, Model model){

        MaterialDto materialDto = materialService.getMaterial(materialKey);

        model.addAttribute("material", materialDto);

        return "meter_detail";
    }
    //관리자 재료페이지[전체]
    @GetMapping("/adminMater")
    public String adminMater(Model model, @PageableDefault(size = 5, sort = "materialKey", direction = Sort.Direction.DESC)Pageable pageable){

        Page<Material> materialDtoList = materialService.pageGetAllMaterial(pageable);
        model.addAttribute("addMater", materialDtoList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", materialService.getListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "재료");
        return "adminMeter";
    }
    //관리자 재료 검색
    @GetMapping("/materialSearch")
    public String adminMaterialSearch(@RequestParam(value="keyword") String keyword, @RequestParam(value="select") String select, Model model,
                                      @PageableDefault(size = 5, sort = "materialKey", direction = Sort.Direction.DESC)Pageable pageable){
        Page<Material> materialDtoList = materialService.pageGetMaterialSearch(keyword, select, pageable);
        model.addAttribute("addMater", materialDtoList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", materialService.getListCheck(pageable));
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "재료");
        return "adminMeter";

    }

    //관리자 재료페이지[카테고리]
    @GetMapping("/adminMaterCate/{materialMainCate}")
    public String adminMaterCate (@PathVariable("materialMainCate") String materialMainCate, Model model,
                                  @PageableDefault(size = 5, sort = "materialKey", direction = Sort.Direction.DESC)Pageable pageable){

        Page<Material> materialDto = materialService.pageGetMainCateMaterial(materialMainCate, pageable);
        model.addAttribute("addMater", materialDto);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", materialService.geCateListCheck( materialMainCate,pageable));
        model.addAttribute("main", materialMainCate);
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "재료");
        return "adminMeterCate";
    }

    //관리자 재료추가페이지
    @GetMapping("/adminMaterialAdd")
    public String adminMaterialAdd(Model model){
        //adminsidebar 설정 용도
        model.addAttribute("adminmenu", "재료");
        return "adminMeterAdd";
    }

    //관리자 재료추가액션
    @PostMapping("/materAdd")
    public String materAdd(@RequestParam("materialImg") MultipartFile materialImg,
                           @RequestParam("materialDeImg") MultipartFile materialDeImg, HttpServletRequest request) throws Exception{

        Date currentTime = new Date();
        String regDateForServer = dateForServer.format(currentTime);

        String materImgName = materialImg.getOriginalFilename();
        String materImgFullName = regDateForServer + "_" + materImgName;

        String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\materialImg";
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        String filePath = savePath + "\\" + materImgFullName;
        materialImg.transferTo(new File(filePath));

        String materDeImgName = materialDeImg.getOriginalFilename();
        String materDeImgFullName = regDateForServer + "_" + materDeImgName;

        String savePath2 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\materialDeImg";
        if (!new File(savePath2).exists()) {
            try{
                new File(savePath2).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        String filePath2 = savePath2 + "\\" + materDeImgFullName;
        materialDeImg.transferTo(new File(filePath2));

        String materialTitle = (String)request.getParameter("materialTitle");
        String materialDetail  = (String)request.getParameter("materialDetail");
        String materialMainCate = (String)request.getParameter("materialMainCate");
        String materialSubCate = (String)request.getParameter("materialSubCate");
        String materialCountry = (String)request.getParameter("materialCountry");
        Integer materialCapacity = Integer.parseInt(request.getParameter("materialCapacity"));
        Integer materialInventory = Integer.parseInt(request.getParameter("materialInventory"));
        String materialDistDate= (String)request.getParameter("materialDistDate");
        String materialSale  = (String)request.getParameter("materialSale");
        String materialUnit = (String)request.getParameter("materialUnit");
        String materialPrice = (String)request.getParameter("materialPrice");

        MaterialDto materialDto = MaterialDto.builder()
                .materialTitle(materialTitle)
                .materialDetail(materialDetail)
                .materialImg(materImgFullName)
                .materialMainCate(materialMainCate)
                .materialSubCate(materialSubCate)
                .materialCountry(materialCountry)
                .materialCapacity(materialCapacity)
                .materialInventory(materialInventory)
                .materialDistDate(materialDistDate)
                .materialSale(materialSale)
                .materialDeImg(materDeImgFullName)
                .materialUnit(materialUnit)
                .materialPrice(materialPrice).build();

        materialService.saveMaterial(materialDto);
        return "redirect:/adminMater";
    }
    //재료 삭제
    @RequestMapping("/adminMaterDelete/{materialKey}")
    public String adminMaterDelete(@PathVariable("materialKey") Long materialKey){

        materialService.adminMaterDelete(materialKey);

        return "redirect:/adminMater";
    }


    //재료 수정
    @PostMapping("/materialUpdate/{materialKey}")
    public String adminMaterialUpdate(@PathVariable("materialKey") Long materialKey, MaterialDto materialDto){
        materialService.adminmaterialUpdate(materialKey, materialDto);

        return "redirect:/adminMater";
    }

}
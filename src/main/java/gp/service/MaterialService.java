package gp.service;


import gp.domain.Material;
import gp.domain.MaterialRepository;
import gp.web.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    //모든 재료 목록 보기
    public List<MaterialDto> getAllMaterial(){
        List<Material> materialEntities = materialRepository.findAll();
        List<MaterialDto> materialDtoList = new ArrayList<>();

        for(Material materialEntity:materialEntities){
            MaterialDto materialDto = MaterialDto.builder()
                    .materialKey(materialEntity.getMaterialKey())
                    .materialTitle(materialEntity.getMaterialTitle())
                    .materialDetail(materialEntity.getMaterialDetail())
                    .materialImg(materialEntity.getMaterialImg())
                    .materialMainCate(materialEntity.getMaterialMainCate())
                    .materialSubCate(materialEntity.getMaterialSubCate())
                    .materialCountry(materialEntity.getMaterialCountry())
                    .materialCapacity(materialEntity.getMaterialCapacity())
                    .materialInventory(materialEntity.getMaterialInventory())
                    .materialDistDate(materialEntity.getMaterialDistDate())
                    .materialSale(materialEntity.getMaterialSale())
                    .materialUnit(materialEntity.getMaterialUnit())
                    .materialPrice(materialEntity.getMaterialPrice()).build();

            materialDtoList.add(materialDto);
        }
        return materialDtoList;
    }

    //종류별 재료 목록 보기
    public List<MaterialDto> getCateMaterial(String materialMainCate){
        List<Material> materialList = materialRepository.findByMaterialMainCate(materialMainCate);
        List<MaterialDto> materialDtoList = new ArrayList<>();

        for(Material materialEntity:materialList){
            MaterialDto materialDto = MaterialDto.builder()
                    .materialKey(materialEntity.getMaterialKey())
                    .materialTitle(materialEntity.getMaterialTitle())
                    .materialDetail(materialEntity.getMaterialDetail())
                    .materialImg(materialEntity.getMaterialImg())
                    .materialMainCate(materialEntity.getMaterialMainCate())
                    .materialSubCate(materialEntity.getMaterialSubCate())
                    .materialCountry(materialEntity.getMaterialCountry())
                    .materialCapacity(materialEntity.getMaterialCapacity())
                    .materialInventory(materialEntity.getMaterialInventory())
                    .materialDistDate(materialEntity.getMaterialDistDate())
                    .materialSale(materialEntity.getMaterialSale())
                    .materialDeImg(materialEntity.getMaterialDeImg())
                    .materialUnit(materialEntity.getMaterialUnit())
                    .materialPrice(materialEntity.getMaterialPrice()).build();
            materialDtoList.add(materialDto);

        }
        return materialDtoList;
    }

    //재료 세부 목록 보기
    public MaterialDto getMaterial(Long materialKey){
        Optional<Material> optionalMaterial = materialRepository.findById(materialKey);
        Material materialEntity = optionalMaterial.get();

        MaterialDto materialDto = MaterialDto.builder()
                .materialKey(materialEntity.getMaterialKey())
                .materialTitle(materialEntity.getMaterialTitle())
                .materialDetail(materialEntity.getMaterialDetail())
                .materialImg(materialEntity.getMaterialImg())
                .materialMainCate(materialEntity.getMaterialMainCate())
                .materialSubCate(materialEntity.getMaterialSubCate())
                .materialCountry(materialEntity.getMaterialCountry())
                .materialCapacity(materialEntity.getMaterialCapacity())
                .materialInventory(materialEntity.getMaterialInventory())
                .materialDistDate(materialEntity.getMaterialDistDate())
                .materialSale(materialEntity.getMaterialSale())
                .materialDeImg(materialEntity.getMaterialDeImg())
                .materialUnit(materialEntity.getMaterialUnit())
                .materialPrice(materialEntity.getMaterialPrice()).build();
        return materialDto;
    }

    //재료 저장하기
    public Long saveMaterial(MaterialDto materialDto){
        return materialRepository.save(materialDto.toEntity()).getMaterialKey();
    }
    //관리자 재료 전체 조회
    @Transactional
    public Page<Material> pageGetAllMaterial(Pageable pageable){

        return materialRepository.findAll(pageable);
    }
    //관리자 재료 페이지 다음장 유무 확인
    @Transactional
    public Boolean getListCheck(Pageable pageable) {
        Page<Material> saved = pageGetAllMaterial(pageable);
        Boolean check = saved.hasNext();

        return check;
    }
    //관리자 재료 카테고리 조회
    @Transactional
    public Page<Material> pageGetMainCateMaterial(String materialMainCate, Pageable pageable){

        Page<Material> materialPage = materialRepository.findByMaterialMainCate(materialMainCate, pageable);


        return materialPage;
    }

    //관리자 재료 페이지 다음장 유무 확인
    @Transactional
    public Boolean geCateListCheck(String materialMainCate,Pageable pageable) {
        Page<Material> saved = pageGetMainCateMaterial(materialMainCate,pageable);
        Boolean check = saved.hasNext();

        return check;
    }
    //관리자 재료 삭제
    @Transactional
    public void adminMaterDelete(Long materialKey){
        Optional<Material> optionalMaterial = materialRepository.findById(materialKey);
        Material material = optionalMaterial.get();

        materialRepository.delete(material);
    }


    @Transactional
    public List<Material> getMaterialByTitles(String[] materialList) {
        return materialRepository.findMaterialList(materialList);
    }

    public List<Material> getMaterialById(String[] materialList) {
        return materialRepository.findMaterialListById(materialList);
    }
}

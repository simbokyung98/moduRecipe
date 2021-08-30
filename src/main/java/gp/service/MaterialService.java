package gp.service;


import gp.domain.Material;
import gp.domain.MaterialRepository;
import gp.web.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                    .materialImg(materialEntity.getMaterialImg()).build();
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
                    .materialImg(materialEntity.getMaterialImg()).build();
            materialDtoList.add(materialDto);

        }
        return materialDtoList;
    }

    //재료 세부 목록 보기
    public MaterialDto getMaterial(Long materialKey){
        Optional<Material> optionalMaterial = materialRepository.findById(materialKey);
        Material material = optionalMaterial.get();

        MaterialDto materialDto = MaterialDto.builder()
                .materialKey(material.getMaterialKey())
                .materialTitle(material.getMaterialTitle())
                .materialImg(material.getMaterialImg()).build();

        return materialDto;
    }




}

package gp.web.dto;


import gp.domain.Material;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaterialDto {


    private Long materialKey;
    private String materialTitle;
    private String materialImg;
    private String materialMainCate;
    private String materialSubCate;
    private String materialCountry;
    private Integer materialCapacity;
    private Integer materialInventory;
    private String materialRegDate;
    private String materialDisDate;
    private String materialSale;

    @Builder
    public MaterialDto(Long materialKey, String materialTitle, String materialImg, String materialMainCate ){
        this.materialKey = materialKey;
        this.materialTitle = materialTitle;
        this.materialImg = materialImg;
        this.materialMainCate = materialMainCate;
    }

    public Material toEntity(){
        return Material.builder()
                .materialKey(materialKey)
                .materialTitle(materialTitle)
                .materialImg(materialImg)
                .materialMainCate(materialMainCate).build();
    }
}

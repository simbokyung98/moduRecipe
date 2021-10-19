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
    private String materialDetail;
    private String materialImg;
    private String materialMainCate;
    private String materialSubCate;
    private String materialCountry;
    private Integer materialCapacity;
    private Integer materialInventory;
    private String materialRegDate;
    private String materialDistDate;
    private String materialSale;
    private String materialDeImg;
    private String materialUnit;
    private String materialPrice;

    @Builder
    public MaterialDto(Long materialKey, String materialTitle, String materialDetail, String materialImg, String materialMainCate,
                       String materialSubCate, String materialCountry, Integer materialCapacity, Integer materialInventory,
                       String materialDistDate, String materialSale, String materialDeImg, String materialUnit, String materialPrice){
        this.materialKey = materialKey;
        this.materialTitle = materialTitle;
        this.materialDetail = materialDetail;
        this.materialImg = materialImg;
        this.materialMainCate = materialMainCate;
        this.materialSubCate = materialSubCate;
        this.materialCountry = materialCountry;
        this.materialCapacity = materialCapacity;
        this.materialInventory = materialInventory;
        //this.materialRegDate = materialRegDate;
        this.materialDistDate = materialDistDate;
        this.materialSale = materialSale;
        this.materialDeImg = materialDeImg;
        this.materialUnit = materialUnit;
        this.materialPrice = materialPrice;
    }

    public Material toEntity(){
        return Material.builder()
                .materialKey(materialKey)
                .materialTitle(materialTitle)
                .materialDetail(materialDetail)
                .materialImg(materialImg)
                .materialMainCate(materialMainCate)
                .materialSubCate(materialSubCate)
                .materialCountry(materialCountry)
                .materialCapacity(materialCapacity)
                .materialInventory(materialInventory)
                //.materialRegDate(materialRegDate)
                .materialDistDate(materialDistDate)
                //.materialSale(materialSale)
                .materialDeImg(materialDeImg)
                .materialUnit(materialUnit)
                .materialPrice(materialPrice).build();
    }
}

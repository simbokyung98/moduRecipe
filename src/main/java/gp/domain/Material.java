package gp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//오토키
    private Long materialKey;

    @Column(length = 40)
    private String materialTitle;

    @Column(length = 45)
    private String materialDetail;

    @Column(length = 40)
    private String materialImg;

    @Column(length = 40)
    private String materialMainCate;

    @Column( length = 40)
    private String materialSubCate;

    @Column(length = 40)
    private String materialCountry;

    private Integer materialCapacity;

    private Integer materialInventory;

    @Column( length = 40)
    private String materialRegDate;

    @Column(length = 40)
    private String materialDistDate;

    @Column(length = 40)
    private String materialSale;

    @Column(length = 45)
    private String materialDeImg;

    @Column(length = 45)
    private String materialUnit;

    @Column(length = 45)
    private String materialPrice;

    @Builder
    public Material( Long materialKey, String materialTitle, String materialDetail, String materialImg, String materialMainCate,
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







}

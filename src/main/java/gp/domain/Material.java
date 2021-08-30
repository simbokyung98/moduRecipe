package gp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Material {

    @Id
    private Long materialKey;

    @Column(length = 40)
    private String materialTitle;

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

    @Builder
    public Material(Long materialKey, String materialTitle, String materialImg, String materialMainCate){
        this.materialKey = materialKey;
        this.materialTitle = materialTitle;
        this.materialImg = materialImg;
        this.materialMainCate = materialMainCate;
    }







}

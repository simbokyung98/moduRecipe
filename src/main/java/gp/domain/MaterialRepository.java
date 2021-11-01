package gp.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    public Page<Material> findByMaterialMainCate(String materialMainCate, Pageable pageable);
    public List<Material> findByMaterialMainCate(String materialMainCate); //***

    public Page<Material> findByMaterialTitleContaining(String MaterialTitle, Pageable pageable);
    public Page<Material> findByMaterialDistDateContaining(String materialDistDate, Pageable pageable);
    public Page<Material> findBymaterialSaleContaining(String materialSale, Pageable pageable);



    @Query(value = "select * from  material where material_Title in (:materialList)", nativeQuery = true)
    List<Material> findMaterialList(@Param("materialList") String[] materialList);

    @Query(value = "select * from  material where material_Key in (:materialList)", nativeQuery = true)
    List<Material> findMaterialListById(@Param("materialList") String[] materialList);
}

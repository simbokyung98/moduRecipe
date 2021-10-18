package gp.domain;

import org.springframework.beans.propertyeditors.PathEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

//    public List<Material> findByMaterialMainCate(String MaterialMainCate);
    public Page<Material> findByMaterialMainCate(String materialMainCate, Pageable pageable);
    public List<Material> findByMaterialMainCate(String materialMainCate);

    public Page<Material> findByMaterialTitleContaining(String MaterialTitle, Pageable pageable);
    public Page<Material> findByMaterialDistDateContaining(String materialDistDate, Pageable pageable);
    public Page<Material> findBymaterialSaleContaining(String materialSale, Pageable pageable);

}

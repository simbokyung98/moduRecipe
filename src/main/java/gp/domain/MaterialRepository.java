package gp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

//    public List<Material> findByMaterialMainCate(String MaterialMainCate);
    public Page<Material> findByMaterialMainCate(String materialMainCate, Pageable pageable);

}

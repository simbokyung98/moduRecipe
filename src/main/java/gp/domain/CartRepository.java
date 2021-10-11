package gp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public List<Cart> findByUsername(String username);

    public Optional<Cart> findByCartMaterial(String cartMaterial);
}

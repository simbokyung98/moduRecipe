package gp.domain;

import gp.web.dto.QnaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {

}

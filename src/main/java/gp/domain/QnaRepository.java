package gp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {

    @Modifying
    @Query(value = "select b from QnaEntity b where answerstate =1")
    List<QnaEntity> find();

    @Query( "UPDATE QnaEntity SET answerstate=1 WHERE answercontent is not null")
    List<QnaEntity> updateAnswerState();

    @Query(value = "select b from QnaEntity b where answerstate =0")
    List<QnaEntity> nullfind();
}

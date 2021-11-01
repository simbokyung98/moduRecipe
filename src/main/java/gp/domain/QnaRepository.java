package gp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {

    @Modifying
    @Query(value = "select b from QnaEntity b where answerstate ='답변완료'")
    List<QnaEntity> find();

    @Transactional
    @Modifying
    @Query( value = "UPDATE QNA SET answer_state='답변완료' where answer_content is not null", nativeQuery = true)
    List<QnaEntity> updateAnswerState();

    @Query(value = "select b from QnaEntity b where answerstate ='답변대기'")
    List<QnaEntity> nullfind();

    public List<QnaEntity> findByqnawriter(String qnawriter);
}

package gp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "qna")
public class QnaEntity {
    @Id
    @Column(name="qaKey",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qakey;

    @Column(name = "qaTitle", nullable = false)
    private String qatitle;

    @Column(name = "qaType",nullable = false)
    private String qatype;

    @Column(name = "qaContent",nullable = false)
    private String qacontent;

    @Column(name = "qaDate",nullable = false)
    private LocalDateTime qadate;

    @Column(name = "qaCount", nullable = false)
    private int qacount;

    @Builder
    public QnaEntity(int qakey, String qatitle, String qatype, String qacontent, LocalDateTime qadate, int qacount) {
        this.qakey=qakey;
        this.qatitle=qatitle;
        this.qatype=qatype;
        this.qacontent=qacontent;
        this.qadate=qadate;
        this.qacount=qacount;

    }

    //업데이트 메소드
    public void update(int qakey, String qatitle, String qatype, String qacontent, LocalDateTime qadate, int qacount){
        this.qakey=qakey;
        this.qatitle=qatitle;
        this.qatype=qatype;
        this.qacontent=qacontent;
        this.qadate=qadate;
        this.qacount=qacount;

    }
}

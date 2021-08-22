package gp.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class NoticeEntity {

    @Id
    @Column(name="notice_Key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticekey;

    @Column(name = "notice_Title", nullable = false)
    private String noticetitle;

    @Column(name = "notice_Content", nullable = false)
    private String noticecontent;

    @Builder
    public NoticeEntity(Long noticekey, String title, String content){
        this.noticekey=noticekey;
        this.noticetitle=title;
        this.noticecontent=content;
    }


    /*@PrePersist
    @PreUpdate
    @PreRemove
    @PostPersist
    @PostUpdate
    @PostRemove
    @PostLoad */
}

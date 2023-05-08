package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "writes")
public class Write {
    @Id @GeneratedValue
    @Column(name = "write_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    private LocalDateTime writeDate;

    @Enumerated(EnumType.STRING)
    private DisclosureType disclosureType;  // 일기 공개 여부 [OPEN, CLOSE]

    //==연관관계 매서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getWrites().add(this);
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
        diary.setWrite(this);
    }
}

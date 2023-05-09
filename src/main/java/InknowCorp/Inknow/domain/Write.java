package InknowCorp.Inknow.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    //==생성 메소드==//
    public static Write createWrite(Member member, Diary diary) {
        Write write = new Write();
        write.setMember(member);
        write.setDiary(diary);

        write.setDisclosureType(DisclosureType.OPEN);
        write.setWriteDate(LocalDateTime.now());
        return write;
    }
}

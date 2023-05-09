package InknowCorp.Inknow.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "writes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public static Write createWrite(Member member, Diary diary, DisclosureType type) {
        Write write = new Write();
        write.setMember(member);
        write.setDiary(diary);

        write.setDisclosureType(type);
        write.setWriteDate(LocalDateTime.now());
        return write;
    }

    //==비즈니스 로직==//
    /*
        일기 공개 여부 변경 (비공개 일기 공개로 변경)
     */
    public void changeDisclosureType() {
        if (this.disclosureType == DisclosureType.OPEN) {
            throw new IllegalStateException("이미 공개로 설정한 일기입니다.");
        }
        this.setDisclosureType(DisclosureType.OPEN);
    }
}

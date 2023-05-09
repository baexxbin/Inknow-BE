package InknowCorp.Inknow.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Diary {
    @Id @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    @OneToOne(mappedBy = "diary", fetch = FetchType.LAZY)
    private Write write;

    private String Content;
    private int heart;
    private int report;

    //==생성 매소드==//
    public static Diary createDiary(Write write, String content) {
        Diary diary = new Diary();
        diary.setWrite(write);
        diary.setContent(content);
        diary.setHeart(0);
        diary.setReport(0);
        return diary;
    }

    /*
        비즈니스 로직
     */

    // 좋아요 수 증가
    public void increaseHeart(int heart) {
        this.heart += heart;
    }

    // 신고 수 누적
    public void increaseReport(int report) {
        this.report += report;
        // 3회 이상일 시 관리자에 알림주기
    }
}

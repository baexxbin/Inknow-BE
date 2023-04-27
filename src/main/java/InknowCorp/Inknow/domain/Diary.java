package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Diary {
    @Id @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    @OneToOne(mappedBy = "diary", fetch = FetchType.LAZY)
    private Write write;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String Content;
    private int heart;
    private int report;
}

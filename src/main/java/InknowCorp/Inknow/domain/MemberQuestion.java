package InknowCorp.Inknow.domain;

import lombok.Lombok;

import javax.persistence.*;

@Entity
public class MemberQuestion {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}

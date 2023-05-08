package InknowCorp.Inknow.domain;

import lombok.Lombok;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private RespondStatus respondStatus;    // 응답상태 [RESPONDED, UNRESPONDED, RESPONSE_EXPIRED]

    private LocalDateTime expiration;
}
